
public class ByteArray {

    private static final byte DEFAULT_SIZE = 16;
    public static final byte BOOLEAN_SIZE = 1;
    public static final byte BYTE_SIZE = 1;
    public static final byte CHAR_SIZE = 2;
    public static final byte SHORT_SIZE = 2;
    public static final byte INT_SIZE = 4;
    public static final byte LONG_SIZE = 8;
    private int currentPos;
    private byte[] data;

    public ByteArray() {
        this(16);
    }

    public ByteArray(int size) {
        currentPos = 0;
        data = new byte[size];
        currentPos = 0;
    }

    public ByteArray(byte[] src) {
        currentPos = 0;
        data = src;
        currentPos = 0;
    }

    public void writeBoolean(boolean val) {
        ensureCapacity(1);
        data[currentPos++] = (byte) (val ? 1 : 0);
    }

    public void writeByte(byte val) {
        ensureCapacity(1);
        data[currentPos++] = val;
    }

    public void writeByte(int val) {
        writeByte((byte) val);
    }

    public void writeChar(char c) {
        ensureCapacity(2);
        data[currentPos + 1] = (byte) (c >>> 0);
        data[currentPos + 0] = (byte) (c >>> 8);
        currentPos += 2;
    }

    public void writeShort(short val) {
        ensureCapacity(2);
        data[currentPos + 1] = (byte) (val >>> 0);
        data[currentPos + 0] = (byte) (val >>> 8);
        currentPos += 2;
    }

    public void writeShort(int val) {
        writeShort((short) val);
    }

    public void writeInt(int val) {
        ensureCapacity(4);
        data[currentPos + 3] = (byte) (val >>> 0);
        data[currentPos + 2] = (byte) (val >>> 8);
        data[currentPos + 1] = (byte) (val >>> 16);
        data[currentPos + 0] = (byte) (val >>> 24);
        currentPos += 4;
    }

    public void writeLong(long val) {
        ensureCapacity(8);
        data[currentPos + 7] = (byte) ((int) (val >>> 0));
        data[currentPos + 6] = (byte) ((int) (val >>> 8));
        data[currentPos + 5] = (byte) ((int) (val >>> 16));
        data[currentPos + 4] = (byte) ((int) (val >>> 24));
        data[currentPos + 3] = (byte) ((int) (val >>> 32));
        data[currentPos + 2] = (byte) ((int) (val >>> 40));
        data[currentPos + 1] = (byte) ((int) (val >>> 48));
        data[currentPos + 0] = (byte) ((int) (val >>> 56));
        currentPos += 8;
    }

    public void writeByteArray(byte[] src) {
        if (src != null) {
            ensureCapacity(src.length);
            System.arraycopy(src, 0, data, currentPos, src.length);
            currentPos += src.length;
        }
    }

    public void writeUTF(String str) {
        writeByteArray(getByteArrFromUTF(str));
    }

    public boolean readBoolean() {
        return data[currentPos++] != 0;
    }

    public byte readByte() {
        return data[currentPos++];
    }

    public char readChar() {
        char c = (char) (((data[currentPos + 1] & 255) << 0) + ((data[currentPos + 0] & 255) << 8));
        currentPos += 2;
        return c;
    }

    public short readShort() {
        short s = (short) (((data[currentPos + 1] & 255) << 0) + ((data[currentPos + 0] & 255) << 8));
        currentPos += 2;
        return s;
    }

    public int readInt() {
        int i = ((data[currentPos + 3] & 255) << 0) + ((data[currentPos + 2] & 255) << 8) + ((data[currentPos + 1] & 255) << 16) + ((data[currentPos + 0] & 255) << 24);
        currentPos += 4;
        return i;
    }

    public long readLong() {
        long l = (((long) data[currentPos + 7] & 255L) << 0) + (((long) data[currentPos + 6] & 255L) << 8) + (((long) data[currentPos + 5] & 255L) << 16) + (((long) data[currentPos + 4] & 255L) << 24) + (((long) data[currentPos + 3] & 255L) << 32) + (((long) data[currentPos + 2] & 255L) << 40) + (((long) data[currentPos + 1] & 255L) << 48) + (((long) data[currentPos + 0] & 255L) << 56);
        currentPos += 8;
        return l;
    }

    public byte[] readByteArray(int length) {
        if (length == -1 || currentPos + length > data.length) {
            length = data.length - currentPos;
        }

        byte[] temp = new byte[length];
        System.arraycopy(data, currentPos, temp, 0, length);
        currentPos += length;
        return temp;
    }

    public byte[] readByteArray(int off, int length) {
        if (length == -1 || off + length > data.length) {
            length = data.length - off;
        }

        byte[] temp = new byte[length];
        System.arraycopy(data, off, temp, 0, length);
        return temp;
    }

    public String readUTF2() {
        int count = 0;
        byte[] bytearr = data;
        int c = 0;
        int char2 = 0;
        int char3 = 0;
        StringBuffer sb = new StringBuffer();

        while (count < data.length) {
            c = bytearr[count] & 255;
            switch (c >> 4) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    ++count;
                    sb.append((char) c);
                case 8:
                case 9:
                case 10:
                case 11:
                default:
                    break;
                case 12:
                case 13:
                    count += 2;
                    char2 = bytearr[count - 1];
                    sb.append((char) ((c & 31) << 6 | char2 & 63));
                    break;
                case 14:
                    count += 3;
                    char2 = bytearr[count - 2];
                    char3 = bytearr[count - 1];
                    sb.append((char) ((c & 15) << 12 | (char2 & 63) << 6 | (char3 & 63) << 0));
            }
        }

        return sb.toString();
    }

    public String readUTF() {
        int utflen = readUnsignedShort();
        if (utflen == -1) {
            System.err.println("Error!! ByteArray: readUTF()");
            return "ERROR";
        } else {
            char[] chararr = null;
            byte[] bytearr = readByteArray(utflen);
            if (utflen > bytearr.length) {
                return null;
            } else {
                chararr = new char[utflen];
                int count = 0;

                int c;
                int chararr_count;
                for (chararr_count = 0; count < utflen; chararr[chararr_count++] = (char) c) {
                    c = bytearr[count] & 255;
                    if (c > 127) {
                        break;
                    }

                    ++count;
                }

                while (count < utflen) {
                    c = bytearr[count] & 255;
                    byte char2;
                    switch (c >> 4) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            ++count;
                            chararr[chararr_count++] = (char) c;
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        default:
                            break;
                        case 12:
                        case 13:
                            count += 2;
                            char2 = bytearr[count - 1];
                            chararr[chararr_count++] = (char) ((c & 31) << 6 | char2 & 63);
                            break;
                        case 14:
                            count += 3;
                            char2 = bytearr[count - 2];
                            int char3 = bytearr[count - 1];
                            chararr[chararr_count++] = (char) ((c & 15) << 12 | (char2 & 63) << 6 | (char3 & 63) << 0);
                    }
                }

                return new String(chararr, 0, chararr_count);
            }
        }
    }

    private void ensureCapacity(int length) {
        if (currentPos + length >= data.length) {
            byte[] tmp = new byte[data.length + 2 * length];
            System.arraycopy(data, 0, tmp, 0, data.length);
            data = tmp;
        }

    }

    public static byte[] getByteArrFromUTF(String str) {
        int strlen = str.length();
        int utflen = 0;
        int count = 0;

        char c;
        for (int i = 0; i < strlen; ++i) {
            c = str.charAt(i);
            if (c >= 1 && c <= 127) {
                ++utflen;
            } else if (c > 2047) {
                utflen += 3;
            } else {
                utflen += 2;
            }
        }

        byte[] bytearr = new byte[utflen + 2];
        bytearr[count++] = (byte) (utflen >>> 8 & 255);
        bytearr[count++] = (byte) (utflen >>> 0 & 255);
        int i = 0;
        for (i = 0; i < strlen; ++i) {
            c = str.charAt(i);
            if (c < 1 || c > 127) {
                break;
            }

            bytearr[count++] = (byte) c;
        }

        for (; i < strlen; ++i) {
            c = str.charAt(i);
            if (c >= 1 && c <= 127) {
                bytearr[count++] = (byte) c;
            } else if (c > 2047) {
                bytearr[count++] = (byte) (224 | c >> 12 & 15);
                bytearr[count++] = (byte) (128 | c >> 6 & 63);
                bytearr[count++] = (byte) (128 | c >> 0 & 63);
            } else {
                bytearr[count++] = (byte) (192 | c >> 6 & 31);
                bytearr[count++] = (byte) (128 | c >> 0 & 63);
            }
        }

        return bytearr;
    }

    private int readUnsignedByte() {
        return data[currentPos++] & 255;
    }

    private int readUnsignedShort() {
        int ch1 = readUnsignedByte();
        int ch2 = readUnsignedByte();
        return (ch1 | ch2) < 0 ? -1 : (ch1 << 8) + (ch2 << 0);
    }

    public byte[] toByteArray() {
        return currentPos < data.length ? readByteArray(0, currentPos) : data;
    }

    public void resetPosition() {
        currentPos = 0;
    }

    public void close() {
        data = null;
    }

    public static int[] bytesToInts(byte[] bytes) {
        if (bytes != null && bytes.length >= 4) {
            int[] ints = new int[bytes.length >> 2];
            ByteArray ba = new ByteArray(bytes);

            for (int i = 0; i < ints.length; ++i) {
                ints[i] = ba.readInt();
            }

            return ints;
        } else {
            return null;
        }
    }

    public static byte[] intsToBytes(int[] ints) {
        if (ints != null && ints.length > 0) {
            byte[] bytes = new byte[ints.length << 2];
            ByteArray ba = new ByteArray(bytes);

            for (int i = 0; i < ints.length; ++i) {
                ba.writeInt(ints[i]);
            }

            return ba.toByteArray();
        } else {
            return null;
        }
    }
}
