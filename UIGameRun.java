import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class UIGameRun {
  private static Image[] creatImage;
  
  private static short[][][] creatFrameData;
  
  private static short[][] creatPicData;
  
  private static short[][][] creatMotionDataAll;
  
  public static int creatFrameIndex;
  
  public static Image encryptImg = null;
  
  public static final int getWait() {
    try {
      if (encryptImg == null)
        encryptImg = Util.loadImage(Util.readPKG("/uimenu.pkg", "test.png")); 
      return encryptImg.getWidth() + 110;
    } catch (Exception e) {
      MainCanvas.mc.aMidlet.destroyApp(true);
      return encryptImg.getWidth() + 110;
    } 
  }
  
  private static UIGameRun instance = null;
  
  public static Image imgSkillCannotUse;
  
  public static UIGameRun getInstance() {
    if (instance == null)
      instance = new UIGameRun(); 
    return instance;
  }
  
  public static void releaseSource() {
    instance = null;
  }
  
  public static void releaseChat() {
    beginChat();
  }
  
  public void drawHPMPBar(Graphics g, GameObj obj, int startX, int startY, boolean isTeam) {
    // Byte code:
    //   0: aload_2
    //   1: getfield type : B
    //   4: iconst_5
    //   5: if_icmpne -> 261
    //   8: ldc ''
    //   10: astore #6
    //   12: aload_2
    //   13: getfield imgID : B
    //   16: tableswitch default -> 145, 0 -> 48, 1 -> 73, 2 -> 98, 3 -> 123
    //   48: new java/lang/StringBuffer
    //   51: dup
    //   52: invokespecial <init> : ()V
    //   55: aload #6
    //   57: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   60: ldc '矿类'
    //   62: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   65: invokevirtual toString : ()Ljava/lang/String;
    //   68: astore #6
    //   70: goto -> 145
    //   73: new java/lang/StringBuffer
    //   76: dup
    //   77: invokespecial <init> : ()V
    //   80: aload #6
    //   82: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   85: ldc '木类'
    //   87: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   90: invokevirtual toString : ()Ljava/lang/String;
    //   93: astore #6
    //   95: goto -> 145
    //   98: new java/lang/StringBuffer
    //   101: dup
    //   102: invokespecial <init> : ()V
    //   105: aload #6
    //   107: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   110: ldc '草类'
    //   112: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   115: invokevirtual toString : ()Ljava/lang/String;
    //   118: astore #6
    //   120: goto -> 145
    //   123: new java/lang/StringBuffer
    //   126: dup
    //   127: invokespecial <init> : ()V
    //   130: aload #6
    //   132: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   135: ldc '皮类'
    //   137: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   140: invokevirtual toString : ()Ljava/lang/String;
    //   143: astore #6
    //   145: new java/lang/StringBuffer
    //   148: dup
    //   149: invokespecial <init> : ()V
    //   152: aload #6
    //   154: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   157: aload_2
    //   158: getfield level : B
    //   161: invokevirtual append : (I)Ljava/lang/StringBuffer;
    //   164: ldc '级'
    //   166: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   169: invokevirtual toString : ()Ljava/lang/String;
    //   172: astore #6
    //   174: aload_1
    //   175: iconst_0
    //   176: invokevirtual setColor : (I)V
    //   179: aload_1
    //   180: aload #6
    //   182: getstatic MainCanvas.screenW : I
    //   185: aload_1
    //   186: invokevirtual getFont : ()Ljavax/microedition/lcdui/Font;
    //   189: aload #6
    //   191: invokevirtual stringWidth : (Ljava/lang/String;)I
    //   194: isub
    //   195: bipush #11
    //   197: isub
    //   198: bipush #12
    //   200: iconst_0
    //   201: invokevirtual drawString : (Ljava/lang/String;III)V
    //   204: aload_1
    //   205: aload #6
    //   207: getstatic MainCanvas.screenW : I
    //   210: aload_1
    //   211: invokevirtual getFont : ()Ljavax/microedition/lcdui/Font;
    //   214: aload #6
    //   216: invokevirtual stringWidth : (Ljava/lang/String;)I
    //   219: isub
    //   220: bipush #10
    //   222: isub
    //   223: bipush #13
    //   225: iconst_0
    //   226: invokevirtual drawString : (Ljava/lang/String;III)V
    //   229: aload_1
    //   230: ldc 16777173
    //   232: invokevirtual setColor : (I)V
    //   235: aload_1
    //   236: aload #6
    //   238: getstatic MainCanvas.screenW : I
    //   241: aload_1
    //   242: invokevirtual getFont : ()Ljavax/microedition/lcdui/Font;
    //   245: aload #6
    //   247: invokevirtual stringWidth : (Ljava/lang/String;)I
    //   250: isub
    //   251: bipush #10
    //   253: isub
    //   254: bipush #12
    //   256: iconst_0
    //   257: invokevirtual drawString : (Ljava/lang/String;III)V
    //   260: return
    //   261: iconst_3
    //   262: istore #6
    //   264: bipush #44
    //   266: istore #7
    //   268: iload #5
    //   270: ifeq -> 277
    //   273: bipush #19
    //   275: istore #7
    //   277: iload #4
    //   279: iconst_3
    //   280: iadd
    //   281: istore #8
    //   283: iload #4
    //   285: bipush #7
    //   287: iadd
    //   288: istore #9
    //   290: aload_2
    //   291: getfield maxHp : I
    //   294: istore #12
    //   296: aload_2
    //   297: getfield curHp : I
    //   300: istore #13
    //   302: aload_2
    //   303: getfield curMp : I
    //   306: istore #14
    //   308: aload_2
    //   309: getfield maxMp : I
    //   312: istore #15
    //   314: iload #5
    //   316: ifeq -> 405
    //   319: aload_1
    //   320: getstatic MainCanvas.mImgUI : [LMImage;
    //   323: bipush #24
    //   325: aaload
    //   326: getfield img : Ljavax/microedition/lcdui/Image;
    //   329: iconst_0
    //   330: iconst_0
    //   331: bipush #40
    //   333: getstatic MainCanvas.mImgUI : [LMImage;
    //   336: bipush #24
    //   338: aaload
    //   339: getfield img : Ljavax/microedition/lcdui/Image;
    //   342: invokevirtual getHeight : ()I
    //   345: iload_3
    //   346: iload #4
    //   348: iconst_0
    //   349: invokestatic drawRegion : (Ljavax/microedition/lcdui/Graphics;Ljavax/microedition/lcdui/Image;IIIIIII)V
    //   352: aload_1
    //   353: getstatic MainCanvas.mImgUI : [LMImage;
    //   356: bipush #24
    //   358: aaload
    //   359: getfield img : Ljavax/microedition/lcdui/Image;
    //   362: getstatic MainCanvas.mImgUI : [LMImage;
    //   365: bipush #24
    //   367: aaload
    //   368: getfield img : Ljavax/microedition/lcdui/Image;
    //   371: invokevirtual getWidth : ()I
    //   374: bipush #6
    //   376: isub
    //   377: iconst_0
    //   378: bipush #6
    //   380: getstatic MainCanvas.mImgUI : [LMImage;
    //   383: bipush #24
    //   385: aaload
    //   386: getfield img : Ljavax/microedition/lcdui/Image;
    //   389: invokevirtual getHeight : ()I
    //   392: iload_3
    //   393: bipush #40
    //   395: iadd
    //   396: iload #4
    //   398: iconst_0
    //   399: invokestatic drawRegion : (Ljavax/microedition/lcdui/Graphics;Ljavax/microedition/lcdui/Image;IIIIIII)V
    //   402: goto -> 421
    //   405: getstatic MainCanvas.mImgUI : [LMImage;
    //   408: bipush #24
    //   410: aaload
    //   411: aload_1
    //   412: iload_3
    //   413: iload #4
    //   415: bipush #20
    //   417: iconst_0
    //   418: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIII)V
    //   421: aload_2
    //   422: getfield type : B
    //   425: iconst_2
    //   426: if_icmpne -> 599
    //   429: getstatic MainCanvas.mImgUI : [LMImage;
    //   432: bipush #32
    //   434: aaload
    //   435: aload_1
    //   436: iload_3
    //   437: iconst_3
    //   438: iadd
    //   439: iload #4
    //   441: iconst_4
    //   442: iadd
    //   443: bipush #9
    //   445: iconst_0
    //   446: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   449: aload_2
    //   450: getfield eliteType : B
    //   453: tableswitch default -> 576, 0 -> 484, 1 -> 507, 2 -> 530, 3 -> 553
    //   484: getstatic MainCanvas.mImgUI : [LMImage;
    //   487: bipush #32
    //   489: aaload
    //   490: aload_1
    //   491: iload_3
    //   492: iconst_3
    //   493: iadd
    //   494: iload #4
    //   496: iconst_4
    //   497: iadd
    //   498: bipush #9
    //   500: iconst_0
    //   501: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   504: goto -> 652
    //   507: getstatic MainCanvas.mImgUI : [LMImage;
    //   510: bipush #32
    //   512: aaload
    //   513: aload_1
    //   514: iload_3
    //   515: iconst_3
    //   516: iadd
    //   517: iload #4
    //   519: iconst_4
    //   520: iadd
    //   521: bipush #10
    //   523: iconst_0
    //   524: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   527: goto -> 652
    //   530: getstatic MainCanvas.mImgUI : [LMImage;
    //   533: bipush #32
    //   535: aaload
    //   536: aload_1
    //   537: iload_3
    //   538: iconst_3
    //   539: iadd
    //   540: iload #4
    //   542: iconst_4
    //   543: iadd
    //   544: bipush #11
    //   546: iconst_0
    //   547: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   550: goto -> 652
    //   553: getstatic MainCanvas.mImgUI : [LMImage;
    //   556: bipush #32
    //   558: aaload
    //   559: aload_1
    //   560: iload_3
    //   561: iconst_3
    //   562: iadd
    //   563: iload #4
    //   565: iconst_4
    //   566: iadd
    //   567: bipush #12
    //   569: iconst_0
    //   570: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   573: goto -> 652
    //   576: getstatic MainCanvas.mImgUI : [LMImage;
    //   579: bipush #32
    //   581: aaload
    //   582: aload_1
    //   583: iload_3
    //   584: iconst_3
    //   585: iadd
    //   586: iload #4
    //   588: iconst_4
    //   589: iadd
    //   590: bipush #9
    //   592: iconst_0
    //   593: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   596: goto -> 652
    //   599: aload_2
    //   600: getfield type : B
    //   603: iconst_3
    //   604: if_icmpne -> 630
    //   607: getstatic MainCanvas.mImgUI : [LMImage;
    //   610: bipush #32
    //   612: aaload
    //   613: aload_1
    //   614: iload_3
    //   615: iconst_3
    //   616: iadd
    //   617: iload #4
    //   619: iconst_4
    //   620: iadd
    //   621: bipush #13
    //   623: iconst_0
    //   624: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   627: goto -> 652
    //   630: getstatic MainCanvas.mImgUI : [LMImage;
    //   633: bipush #32
    //   635: aaload
    //   636: aload_1
    //   637: iload_3
    //   638: iconst_3
    //   639: iadd
    //   640: iload #4
    //   642: iconst_4
    //   643: iadd
    //   644: aload_2
    //   645: getfield originalImgID : B
    //   648: iconst_0
    //   649: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   652: aload_2
    //   653: getfield objID : I
    //   656: getstatic MainCanvas.mc : LMainCanvas;
    //   659: getfield teamLeaderId : I
    //   662: if_icmpne -> 680
    //   665: getstatic MainCanvas.mImgUI : [LMImage;
    //   668: bipush #34
    //   670: aaload
    //   671: aload_1
    //   672: iload_3
    //   673: iload #4
    //   675: iconst_0
    //   676: iconst_0
    //   677: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   680: aload_2
    //   681: invokestatic getInstance : ()LPlayer;
    //   684: if_acmpeq -> 703
    //   687: aload_2
    //   688: getfield type : B
    //   691: iconst_3
    //   692: if_icmpeq -> 703
    //   695: aload_2
    //   696: getfield type : B
    //   699: iconst_1
    //   700: if_icmpne -> 799
    //   703: aload_2
    //   704: getfield level : B
    //   707: bipush #10
    //   709: if_icmplt -> 769
    //   712: getstatic MainCanvas.mImgUI : [LMImage;
    //   715: bipush #12
    //   717: aaload
    //   718: aload_1
    //   719: iload_3
    //   720: bipush #23
    //   722: iadd
    //   723: iload #4
    //   725: bipush #11
    //   727: iadd
    //   728: aload_2
    //   729: getfield level : B
    //   732: bipush #10
    //   734: idiv
    //   735: iconst_0
    //   736: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   739: getstatic MainCanvas.mImgUI : [LMImage;
    //   742: bipush #12
    //   744: aaload
    //   745: aload_1
    //   746: iload_3
    //   747: bipush #28
    //   749: iadd
    //   750: iload #4
    //   752: bipush #11
    //   754: iadd
    //   755: aload_2
    //   756: getfield level : B
    //   759: bipush #10
    //   761: irem
    //   762: iconst_0
    //   763: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   766: goto -> 975
    //   769: getstatic MainCanvas.mImgUI : [LMImage;
    //   772: bipush #12
    //   774: aaload
    //   775: aload_1
    //   776: iload_3
    //   777: bipush #25
    //   779: iadd
    //   780: iload #4
    //   782: bipush #11
    //   784: iadd
    //   785: aload_2
    //   786: getfield level : B
    //   789: bipush #10
    //   791: irem
    //   792: iconst_0
    //   793: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   796: goto -> 975
    //   799: aload_2
    //   800: getfield type : B
    //   803: iconst_2
    //   804: if_icmpeq -> 835
    //   807: aload_2
    //   808: getfield type : B
    //   811: iconst_1
    //   812: if_icmpne -> 975
    //   815: aload_2
    //   816: getfield group : B
    //   819: ifeq -> 975
    //   822: invokestatic getInstance : ()LPlayer;
    //   825: getfield group : B
    //   828: aload_2
    //   829: getfield group : B
    //   832: if_icmpeq -> 975
    //   835: aload_2
    //   836: getfield level : B
    //   839: invokestatic getInstance : ()LPlayer;
    //   842: getfield level : B
    //   845: isub
    //   846: bipush #8
    //   848: if_icmpge -> 858
    //   851: aload_2
    //   852: getfield level : B
    //   855: ifge -> 882
    //   858: getstatic MainCanvas.mImgUI : [LMImage;
    //   861: bipush #36
    //   863: aaload
    //   864: aload_1
    //   865: iload_3
    //   866: bipush #22
    //   868: iadd
    //   869: iload #4
    //   871: bipush #10
    //   873: iadd
    //   874: iconst_0
    //   875: iconst_0
    //   876: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   879: goto -> 975
    //   882: aload_2
    //   883: getfield level : B
    //   886: bipush #10
    //   888: if_icmplt -> 948
    //   891: getstatic MainCanvas.mImgUI : [LMImage;
    //   894: bipush #12
    //   896: aaload
    //   897: aload_1
    //   898: iload_3
    //   899: bipush #23
    //   901: iadd
    //   902: iload #4
    //   904: bipush #11
    //   906: iadd
    //   907: aload_2
    //   908: getfield level : B
    //   911: bipush #10
    //   913: idiv
    //   914: iconst_0
    //   915: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   918: getstatic MainCanvas.mImgUI : [LMImage;
    //   921: bipush #12
    //   923: aaload
    //   924: aload_1
    //   925: iload_3
    //   926: bipush #28
    //   928: iadd
    //   929: iload #4
    //   931: bipush #11
    //   933: iadd
    //   934: aload_2
    //   935: getfield level : B
    //   938: bipush #10
    //   940: irem
    //   941: iconst_0
    //   942: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   945: goto -> 975
    //   948: getstatic MainCanvas.mImgUI : [LMImage;
    //   951: bipush #12
    //   953: aaload
    //   954: aload_1
    //   955: iload_3
    //   956: bipush #25
    //   958: iadd
    //   959: iload #4
    //   961: bipush #11
    //   963: iadd
    //   964: aload_2
    //   965: getfield level : B
    //   968: bipush #10
    //   970: irem
    //   971: iconst_0
    //   972: invokevirtual draw : (Ljavax/microedition/lcdui/Graphics;IIIZ)V
    //   975: iload #12
    //   977: ifne -> 987
    //   980: iload #7
    //   982: istore #10
    //   984: goto -> 1006
    //   987: iload #12
    //   989: iload #13
    //   991: isub
    //   992: bipush #100
    //   994: imul
    //   995: iload #12
    //   997: idiv
    //   998: iload #7
    //   1000: imul
    //   1001: bipush #100
    //   1003: idiv
    //   1004: istore #10
    //   1006: iload #15
    //   1008: ifne -> 1018
    //   1011: iload #7
    //   1013: istore #11
    //   1015: goto -> 1037
    //   1018: iload #15
    //   1020: iload #14
    //   1022: isub
    //   1023: bipush #100
    //   1025: imul
    //   1026: iload #15
    //   1028: idiv
    //   1029: iload #7
    //   1031: imul
    //   1032: bipush #100
    //   1034: idiv
    //   1035: istore #11
    //   1037: aload_1
    //   1038: ldc 3879194
    //   1040: invokevirtual setColor : (I)V
    //   1043: aload_1
    //   1044: iload_3
    //   1045: bipush #24
    //   1047: iadd
    //   1048: iload #7
    //   1050: iadd
    //   1051: iload #10
    //   1053: isub
    //   1054: iload #8
    //   1056: iload #10
    //   1058: iconst_3
    //   1059: invokevirtual fillRect : (IIII)V
    //   1062: aload_2
    //   1063: getfield type : B
    //   1066: iconst_4
    //   1067: if_icmpeq -> 1078
    //   1070: aload_2
    //   1071: getfield type : B
    //   1074: iconst_1
    //   1075: if_icmpne -> 1099
    //   1078: aload_1
    //   1079: iload_3
    //   1080: bipush #24
    //   1082: iadd
    //   1083: iload #7
    //   1085: iadd
    //   1086: iload #11
    //   1088: isub
    //   1089: iload #9
    //   1091: iconst_1
    //   1092: isub
    //   1093: iload #11
    //   1095: iconst_3
    //   1096: invokevirtual fillRect : (IIII)V
    //   1099: iload #5
    //   1101: ifne -> 1119
    //   1104: aload_0
    //   1105: aload_1
    //   1106: aload_2
    //   1107: iload_3
    //   1108: bipush #22
    //   1110: iadd
    //   1111: iload #4
    //   1113: bipush #24
    //   1115: iadd
    //   1116: invokevirtual drawBuffer : (Ljavax/microedition/lcdui/Graphics;LGameObj;II)V
    //   1119: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #82	-> 0
    //   #83	-> 8
    //   #84	-> 12
    //   #86	-> 48
    //   #87	-> 70
    //   #89	-> 73
    //   #90	-> 95
    //   #93	-> 98
    //   #94	-> 120
    //   #96	-> 123
    //   #100	-> 145
    //   #106	-> 174
    //   #107	-> 179
    //   #108	-> 186
    //   #107	-> 201
    //   #110	-> 204
    //   #111	-> 211
    //   #110	-> 226
    //   #113	-> 229
    //   #114	-> 235
    //   #115	-> 242
    //   #114	-> 257
    //   #117	-> 260
    //   #121	-> 261
    //   #122	-> 264
    //   #123	-> 268
    //   #124	-> 273
    //   #126	-> 277
    //   #127	-> 283
    //   #130	-> 290
    //   #131	-> 296
    //   #132	-> 302
    //   #133	-> 308
    //   #136	-> 314
    //   #137	-> 319
    //   #138	-> 342
    //   #137	-> 349
    //   #139	-> 352
    //   #140	-> 371
    //   #141	-> 389
    //   #139	-> 399
    //   #144	-> 405
    //   #148	-> 421
    //   #149	-> 429
    //   #152	-> 449
    //   #154	-> 484
    //   #155	-> 504
    //   #157	-> 507
    //   #158	-> 524
    //   #160	-> 527
    //   #162	-> 530
    //   #163	-> 547
    //   #164	-> 550
    //   #167	-> 553
    //   #168	-> 570
    //   #169	-> 573
    //   #171	-> 576
    //   #172	-> 596
    //   #176	-> 599
    //   #177	-> 607
    //   #183	-> 630
    //   #188	-> 652
    //   #194	-> 665
    //   #198	-> 680
    //   #201	-> 703
    //   #202	-> 712
    //   #204	-> 739
    //   #207	-> 769
    //   #212	-> 799
    //   #215	-> 822
    //   #216	-> 835
    //   #219	-> 858
    //   #222	-> 882
    //   #223	-> 891
    //   #225	-> 918
    //   #228	-> 948
    //   #235	-> 975
    //   #236	-> 980
    //   #238	-> 987
    //   #240	-> 1006
    //   #242	-> 1011
    //   #244	-> 1018
    //   #246	-> 1037
    //   #247	-> 1043
    //   #249	-> 1062
    //   #251	-> 1078
    //   #254	-> 1099
    //   #255	-> 1104
    //   #257	-> 1119
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   12	249	6	tmpStr	Ljava/lang/String;
    //   984	3	10	disHpBarLen	I
    //   1015	3	11	disMpBarLen	I
    //   0	1120	0	this	LUIGameRun;
    //   0	1120	1	g	Ljavax/microedition/lcdui/Graphics;
    //   0	1120	2	obj	LGameObj;
    //   0	1120	3	startX	I
    //   0	1120	4	startY	I
    //   0	1120	5	isTeam	Z
    //   264	856	6	barH	B
    //   268	852	7	barLen	B
    //   283	837	8	hpBarY	I
    //   290	830	9	mpBarY	I
    //   1006	114	10	disHpBarLen	I
    //   1037	83	11	disMpBarLen	I
    //   296	824	12	maxHp	I
    //   302	818	13	hp	I
    //   308	812	14	mp	I
    //   314	806	15	maxMp	I
  }
  
  public void drawLetterInfo(Graphics g) {
    if (MainCanvas.isHaveNewMail)
      MainCanvas.mImgUI[13].draw(g, 2, 221, 0, false); 
  }
  
  public void drawFinishedTask(Graphics g) {
    if (MainCanvas.isHaveFinishedTask)
      MainCanvas.mImgUI[13].draw(g, 19, 221, 4, false); 
  }
  
  public void drawAttributes(Graphics g) {
    if (MainCanvas.isHaveAttributes)
      MainCanvas.mImgUI[13].draw(g, 36, 221, 3, false); 
  }
  
  public void drawArenaStatus(Graphics g) {
    switch (MainCanvas.mc.arenaStatus) {
      case 1:
        MainCanvas.mImgUI[13].draw(g, 53, 221, 5, false);
        break;
      case 2:
        MainCanvas.mImgUI[13].draw(g, 53, 221, 6, false);
        break;
    } 
  }
  
  public void drawBuffer(Graphics g, GameObj obj, int startX, int startY) {
    int bufferNum = 0;
    for (int i = 0, kk = obj.bufferState.length; i < kk; i++) {
      if (obj.bufferState[i]) {
        MainCanvas.mImgUI[33].draw(g, startX + (MainCanvas.mImgUI[33]).frame_w * bufferNum % 4, startY + ((MainCanvas.mImgUI[33]).frame_w + 1) * (bufferNum >> 2), Cons.BUFFER_SKETCH_MAP[i], false);
        bufferNum++;
      } 
    } 
  }
  
  public void drawExpBar(Graphics g) {
    if (!Cons.showExpBar)
      return; 
    int XP_START_Y = MainCanvas.screenH - 25;
    short XP_START_X = 0;
    short XP_BAR_H = 4;
    short XP_BAR_W = (short)MainCanvas.screenW;
    short XP_BLANK_W = 2;
    g.setColor(0);
    g.fillRect(0, XP_START_Y, XP_BAR_W, 4);
    g.setColor(8415039);
    g.drawRect(0, XP_START_Y, XP_BAR_W - 1, 4);
    g.setColor(16777215);
    long w = 0L;
    int exp = (Player.getInstance()).exp;
    int maxXp = (Player.getInstance()).maxExp;
    if (maxXp == 0) {
      w = 0L;
    } else {
      w = exp;
      w *= (XP_BAR_W - 4);
      w /= maxXp;
    } 
    g.fillRect(2, XP_START_Y + 2, (int)w, 1);
    g.setColor(8415039);
    for (int i = 1; i < 10; i++)
      g.fillRect(-2 + XP_BAR_W / 10 * i, XP_START_Y + 1, 1, 3); 
  }
  
  public static void drawShortcutBar(Graphics g, int dy, boolean drawCanUse) {
    int barH = 21;
    if (Cons.showShortCut || MainCanvas.mc
      .getRightMenuSubState() == 7) {
      UIRim.drawRim(g, 0, dy - 21, MainCanvas.screenW, 21, (byte)6);
      g.setColor(16716593);
      for (int i = 1; i < 9; i++) {
        int imgId, w = 20 * UIComponent.CURR_W / 176;
        if (w > 20)
          w++; 
        int startX = 11 * UIComponent.CURR_W / 176;
        int x = startX + w * (i - 1);
        int y = dy - 21 + 2;
        int skillIndex = Player.userDefinedSkills[i];
        int profession = (Player.getInstance()).profession - 1;
        if (skillIndex == 0) {
          imgId = 36;
        } else if (skillIndex == -1) {
          imgId = -1;
        } else if (skillIndex > 14) {
          imgId = 33 + skillIndex - 15;
        } else {
          imgId = UISkillTree.SKILL_IMAGE[profession][skillIndex - 1];
        } 
        if (imgId != -1)
          if (skillIndex > 14) {
            MainCanvas.mImgStuff.draw(g, x, y, imgId, false);
          } else {
            MainCanvas.mImgUI[25].draw(g, x, y, imgId, false);
          }  
        MainCanvas.mImgUI[12].draw(g, x + 1, y + 1, i, false);
        if (drawCanUse)
          if (!Player.canUseSkill[i]) {
            if (imgSkillCannotUse == null)
              imgSkillCannotUse = Util.loadImage(Util.readPKG("/uiuse.pkg", "skillpanle.png")); 
            g.drawImage(imgSkillCannotUse, x, y + 1, 20);
          }  
      } 
    } else {
      g.setColor(0);
      g.fillRect(0, MainCanvas.screenH - 21, MainCanvas.screenW, 21);
    } 
  }
  
  public void drawTeamMate(Graphics g) {
    if (!Cons.showTeamMate)
      return; 
    if (MainCanvas.mc.teamMates != null) {
      String tempName = "";
      if (MainCanvas.mc.teamMates.size() > 0)
        for (int i = 0; i < MainCanvas.mc.teamMates.size(); i++) {
          GameObj ob = (GameObj)MainCanvas.mc.teamMates.elementAt(i);
          drawHPMPBar(g, ob, 0, 47 * (i + 1), true);
          tempName = ob.name;
          if (ob.name.length() > 3)
            tempName = ob.name.substring(0, 2) + "..."; 
          g.setColor(0);
          g.drawString(tempName, 5, 33 + 47 * i, 20);
          g.setColor(16777173);
          g.drawString(tempName, 4, 32 + 47 * i, 20);
        }  
    } 
  }
  
  public static void draw(Graphics g) {
    getInstance().drawAll(g);
  }
  
  public void drawAll(Graphics g) {
    for (int i = 0; i < ObjManager.vectorObj.size(); i++)
      ((GameObj)ObjManager.vectorObj.elementAt(i)).drawHpChange(g); 
    drawHPMPBar(g, Player.getInstance(), 25, 0, false);
    drawLetterInfo(g);
    drawFinishedTask(g);
    drawAttributes(g);
    drawArenaStatus(g);
    if (Player.getInstance() != null && ObjManager.showTarget == null) {
      String tmpStr = Map.currentMapName + " (" + (Player.getInstance()).col + ", " + (Player.getInstance()).row + ")";
      g.setColor(0);
      g.drawString(tmpStr, MainCanvas.screenW - g
          .getFont().stringWidth(tmpStr) - 2 + 1, 13, 0);
      g.drawString(tmpStr, MainCanvas.screenW - g
          .getFont().stringWidth(tmpStr) - 2, 13, 0);
      g.setColor(16777173);
      g.drawString(tmpStr, MainCanvas.screenW - g
          .getFont().stringWidth(tmpStr) - 2, 12, 0);
    } 
    if (ObjManager.showTarget != null)
      drawHPMPBar(g, ObjManager.showTarget, 162, 0, false); 
    drawTeamMate(g);
    drawTrumpetMessage(g);
    UIRim.drawRim(g, 0, MainCanvas.screenH - 21, MainCanvas.screenW, 21, (byte)6);
    drawSmallMap(g);
    drawChatBar(g);
    drawShortcutBar(g, MainCanvas.screenH + 2, true);
    drawExpBar(g);
    if (Cons.zeroShortShow >= 0) {
      String show1 = "";
      String show2 = "";
      String show3 = "";
      switch (Cons.zeroShort) {
        case 0:
          show1 = "< 高画质 >";
          show2 = "显示玩家，组队信息和小地图";
          break;
        case 1:
          show1 = "< 中画质 >";
          show2 = "不显示周围玩家的名字";
          break;
        case 2:
          show1 = "< 低画质 >";
          show2 = "不显示玩家，组队信息等";
          break;
      } 
      g.setColor(0);
      g.drawString(show1, (MainCanvas.screenW >> 1) + 1, 21, 17);
      g.drawString(show2, (MainCanvas.screenW >> 1) + 1, 37, 17);
      if (show3 != "")
        g.drawString(show3, (MainCanvas.screenW >> 1) + 1, 53, 17); 
      g.setColor(16777215);
      g.drawString(show1, MainCanvas.screenW >> 1, 20, 17);
      g.drawString(show2, MainCanvas.screenW >> 1, 36, 17);
      if (show3 != "")
        g.drawString(show3, MainCanvas.screenW >> 1, 52, 17); 
      Cons.zeroShortShow = (short)(Cons.zeroShortShow + 1);
      if (Cons.zeroShortShow > 30)
        Cons.zeroShortShow = -1; 
    } 
  }
  
  public char[] chatCharArray = null;
  
  public static int chatCounter = 10;
  
  public static boolean isInit = true;
  
  public byte rows = 0;
  
  public static Vector chatVector = new Vector();
  
  public static Vector chatColorVector = new Vector();
  
  public static final int CHAT_DELAY_TIME = 10;
  
  public void drawChatString(Graphics g, String str, byte color, int x, int y) {
    g.setColor(Cons.chatChannalColor[color]);
    int offsetX = x;
    this.chatCharArray = str.toCharArray();
    int lg = this.chatCharArray.length;
    for (int i = 0; i < lg; i++) {
      if (i != lg - 1 && this.chatCharArray[i] == '#' && this.chatCharArray[i + 1] >= '0' && this.chatCharArray[i + 1] <= '9') {
        MainCanvas.mImgUI[31].draw(g, offsetX, y, this.chatCharArray[i + 1] - 48, false);
        i++;
        offsetX += (MainCanvas.mImgUI[31]).frame_w;
      } else {
        g.drawChar(this.chatCharArray[i], offsetX, y, 20);
        offsetX += g.getFont().charWidth(this.chatCharArray[i]);
      } 
    } 
  }
  
  public static void tickChatBar() {
    chatCounter--;
    if (chatCounter < 0) {
      chatCounter = 10;
      if (chatVector.size() > 3) {
        chatVector.removeElementAt(0);
        chatColorVector.removeElementAt(0);
      } 
    } 
  }
  
  public void drawChatBar(Graphics g) {
    int fontH = 20;
    int shortbarH = 25;
    int firstZi = MainCanvas.screenH - fontH * 3 - shortbarH;
    g.setColor(0);
    g.fillRect(0, firstZi, MainCanvas.screenW, shortbarH * 3);
    g.setColor(8415039);
    g.drawRect(0, firstZi, MainCanvas.screenW - 1, fontH * 3 + 1);
    g.setColor(3681047);
    g.drawLine(0, firstZi + fontH, MainCanvas.screenW, firstZi + fontH);
    g.drawLine(0, firstZi + fontH * 2, MainCanvas.screenW, firstZi + fontH * 2);
    if (chatVector.size() > 0) {
      drawChatString(g, (String)chatVector.elementAt(0), ((Byte)chatColorVector
          .elementAt(0)).byteValue(), 2, firstZi + 2);
      if (chatVector.size() > 1) {
        drawChatString(g, (String)chatVector.elementAt(1), ((Byte)chatColorVector
            .elementAt(1)).byteValue(), 2, firstZi + fontH + 2);
        if (chatVector.size() > 2)
          drawChatString(g, (String)chatVector.elementAt(2), ((Byte)chatColorVector
              .elementAt(2)).byteValue(), 2, firstZi + fontH * 2 + 2); 
      } 
    } 
  }
  
  public static void beginChat() {
    chatCounter = 10;
    isInit = true;
  }
  
  private static final int MAP_START_X = MainCanvas.screenW - 31;
  
  private static final int MAP_START_Y = MainCanvas.screenH - 105;
  
  private static final int MAP_WIDTH = 50;
  
  private static final int MAP_HEIGHT = 25;
  
  int startX = MAP_START_X;
  
  int startY = MAP_START_Y;
  
  int tileWidth = 0;
  
  int tileHeight = 0;
  
  int mapWidth = 0;
  
  int mapHeight = 0;
  
  int mapCols = 0;
  
  int mapRows = 0;
  
  public static void init() {
    getInstance().initSmallMap();
  }
  
  public void initSmallMap() {
    this.mapCols = Map.currentTotalColumn;
    this.mapRows = Map.currentTotalRow;
    if (this.mapCols == 0)
      return; 
    if (this.mapRows == 0)
      return; 
    if (this.mapCols > this.mapRows) {
      this.mapWidth = 50;
      this.tileWidth = this.mapWidth * 1000;
      this.tileWidth /= this.mapCols;
      this.tileHeight = this.tileWidth >> 1;
      this.mapHeight = this.tileHeight * this.mapRows;
      this.mapHeight /= 1000;
    } else {
      this.mapHeight = 25;
      this.tileHeight = this.mapHeight * 1000;
      this.tileHeight /= this.mapRows;
      this.tileWidth = this.tileHeight * 2;
      this.mapWidth = this.tileWidth * this.mapCols;
      this.mapWidth /= 1000;
    } 
    this.startX = MAP_START_X - (this.mapWidth >> 1);
    this.startY = MAP_START_Y - (this.mapHeight >> 1);
  }
  
  private int getMapX(int col, int row) {
    return (col - row) * this.tileWidth / 2000 + (this.mapWidth >> 1) + this.startX;
  }
  
  private int getMapY(int col, int row) {
    return (col + row) * this.tileHeight / 2000 + this.startY;
  }
  
  private void drawSmallMap(Graphics g) {
    if (!Cons.showSmallMap)
      return; 
    int[][] points = { { 0, 0 }, { this.mapCols, 0 }, { this.mapCols, this.mapRows }, { 0, this.mapRows } };
    int[] xPoints = new int[4];
    int[] yPoints = new int[4];
    for (int i = 0; i < 4; i++) {
      xPoints[i] = getMapX(points[i][0], points[i][1]);
      yPoints[i] = getMapY(points[i][0], points[i][1]);
    } 
    g.setColor(16777173);
    g.drawLine(xPoints[0], yPoints[0] + 1, xPoints[1] - 1, yPoints[1]);
    g.drawLine(xPoints[1] - 1, yPoints[1], xPoints[2], yPoints[2] - 1);
    g.drawLine(xPoints[2], yPoints[2] - 1, xPoints[3] + 1, yPoints[3]);
    g.drawLine(xPoints[0], yPoints[0] + 1, xPoints[3] + 1, yPoints[3]);
    if (MainCanvas.isLogin)
      drawDoors(g); 
    drawFlags(g);
    drawTeamMates(g);
  }
  
  private void drawDoors(Graphics g) {
    g.setColor(16524288);
    for (int i = 0, kk = Map.curPointArray.length; i < kk; i++) {
      int col = Map.curPointArray[i][0];
      int row = Map.curPointArray[i][1];
      if (col != -1 || row != -1)
        g.fillRect(getMapX(col, row) - 1, getMapY(col, row) - 1, 3, 3); 
    } 
  }
  
  private void drawTeamMates(Graphics g) {
    g.setColor(130823);
    int col = (Player.getInstance()).col;
    int row = (Player.getInstance()).row;
    g.fillRect(getMapX(col, row), getMapY(col, row), 3, 3);
    g.setColor(87551);
    if (MainCanvas.mc.teamMates != null)
      for (int i = 0; i < MainCanvas.mc.teamMates.size(); i++) {
        GameObj ob = (GameObj)MainCanvas.mc.teamMates.elementAt(i);
        drawOneTeamMate(g, ob);
      }  
  }
  
  private void drawOneTeamMate(Graphics g, GameObj player) {
    if (player == null)
      return; 
    int col = player.col;
    int row = player.row;
    if (col < 0 || row < 0)
      return; 
    g.fillRect(getMapX(col, row), getMapY(col, row), 2, 2);
  }
  
  byte msgNum = 0;
  
  public void drawWaitResInit(Graphics g) {
    this.msgNum = 3;
    MainCanvas.drawGroundback(g);
    drawLoadingTip(g);
    drawTJ(g, this.msgNum, MainCanvas.loadCount);
  }
  
  boolean isExitTip = false;
  
  public void updateMapCount() {
    switch (MainCanvas.loadCount) {
      case 0:
        MainCanvas.templevel = 0;
        if (MainCanvas.mc.topForm != null)
          MainCanvas.mc.topForm = null; 
        if (this.tempTip != null && !this.tempTip.equals(""))
          if (PCChangeMap.isCounterpart && loadingTip != null && 
            !"".equals(loadingTip)) {
            this.drawTip = loadingTip;
            this.isExitTip = true;
          } else if (this.isExitTip) {
            if (loadingTip != null && !"".equals(loadingTip)) {
              this.drawTip = loadingTip;
            } else {
              this.drawTip = this.tempTip = "欢迎来到天劫！";
            } 
            this.isExitTip = false;
          } else {
            this.drawTip = this.tempTip;
          }  
        MainCanvas.mc.setNPCSubState((byte)0);
        break;
      case 21:
        if (PCChangeMap.isReceivedMapInfo) {
          PCChangeMap.isReceivedMapInfo = false;
          MainCanvas.mc.setOtherSubState((byte)2);
          PCChangeMap.creatNpcAndEnemyArray(PCChangeMap.npcNum, PCChangeMap.enemyNum);
          break;
        } 
        return;
      case 23:
        if (PCChangeMap.isReceivedMapData == 0) {
          PCChangeMap.isReceivedMapData = 3;
          MainCanvas.ni.send(536871680);
        } 
        break;
      case 25:
        if (PCChangeMap.nextMapType == 1) {
          if (Map.curMapType == -1) {
            GameObj.initWhitePeopleRes();
            break;
          } 
          if (Map.curMapType == 1)
            break; 
          if (Map.curMapType == 2)
            break; 
          if (Map.curMapType == 3)
            GameObj.releaseBlackRes(); 
          break;
        } 
        if (PCChangeMap.nextMapType == 2) {
          if (Map.curMapType == -1) {
            GameObj.initBlackPeopleRes();
            break;
          } 
          if (Map.curMapType == 1)
            break; 
          if (Map.curMapType == 2)
            break; 
          if (Map.curMapType == 3)
            GameObj.releaseWhiteRes(); 
          break;
        } 
        if (PCChangeMap.nextMapType == 3) {
          if (Map.curMapType == -1) {
            GameObj.initWhitePeopleRes();
            GameObj.initBlackPeopleRes();
            break;
          } 
          if (Map.curMapType == 1) {
            GameObj.initBlackPeopleRes();
            break;
          } 
          if (Map.curMapType == 2) {
            GameObj.initWhitePeopleRes();
            break;
          } 
          if (Map.curMapType == 3);
        } 
        break;
      case 28:
        if (PCChangeMap.isReceivedMapData == 1 || PCChangeMap.isReceivedMapData == 2) {
          Map.getInstance().initMap(PCChangeMap.nextMapID, PCChangeMap.isReceivedMapData);
          PCChangeMap.isReceivedMapData = 0;
          break;
        } 
        MainCanvas.loadCount--;
        break;
      case 29:
        GameObj.initMosterRES(PCChangeMap.arrEnemy);
        break;
      case 32:
        Map.curMapType = PCChangeMap.nextMapType;
        MainCanvas.ni.send(536871424);
        break;
      case 33:
        if (!MainCanvas.isFirst[MainCanvas.choose_manID])
          Map.isDrawPlaceName = true; 
        loadingTip = null;
        PCChangeMap.isParseResMsg = false;
        Map.drawPlaceNameCount = 0;
        Map.colorIndex = 0;
        MainCanvas.loadRequestCount = 0;
        MainCanvas.isDeadLoad = false;
        break;
    } 
    if (MainCanvas.loadCount <= this.msgNum) {
      MainCanvas.loadCount++;
    } else if (PCChangeMap.isParseResMsg) {
      MainCanvas.loadCount++;
    } 
  }
  
  private void drawTJ(Graphics g, int aOffset, int aWaitCnt) {
    short tjPicX = (short)(MainCanvas.screenW >> 1);
    short tjPicY = 33;
    short tjStringY = (short)(MainCanvas.screenH - 28);
    g.setColor(11898450);
    g.drawString("载入中... " + (aWaitCnt * 3 + 1) + "%", MainCanvas.screenW >> 1, tjStringY, 17);
    if (MainCanvas.loadCount - aOffset >= 0)
      if (MainCanvas.loadCount - aOffset < (MainCanvas.tjMotionDataAll[0]).length - 1) {
        if (!PCChangeMap.isCounterpart)
          Util.drawRoleEditFrame(MainCanvas.tjImage, g, MainCanvas.tjFrameData[MainCanvas.tjMotionDataAll[0][MainCanvas.loadCount - aOffset]], MainCanvas.tjPicData, tjPicX, tjPicY, false); 
      } else if (!PCChangeMap.isCounterpart) {
        Util.drawRoleEditFrame(MainCanvas.tjImage, g, MainCanvas.tjFrameData[MainCanvas.tjMotionDataAll[0][(MainCanvas.tjMotionDataAll[0]).length - 1]], MainCanvas.tjPicData, tjPicX, tjPicY, false);
      }  
  }
  
  public void drawWaitFirstResInit(Graphics g) {
    int i;
    g.setColor(0);
    g.fillRect(0, 0, MainCanvas.screenW, MainCanvas.screenH);
    switch (MainCanvas.loadCount) {
      case 0:
        MainCanvas.mc.loadResource();
        break;
      case 2:
        GameObj.imgShadowImmortal = Util.loadImage(Util.readPKG("/uiuse.pkg", "qqsado.png"));
        break;
      case 4:
        GameObj.initSpecificAndEqument();
        break;
      case 5:
        try {
          MainCanvas.waitImg = Image.createImage("/circle.png");
        } catch (IOException iOException) {}
        break;
      case 6:
        GameObj.initNpcRes();
        break;
      case 8:
        MainCanvas.mImgUI[1] = new MImage("ui1.png", "/uiuse.pkg", 7, 7);
        MainCanvas.mImgUI[2] = new MImage("ui2.png", "/uiuse.pkg", 10, 10);
        MainCanvas.mImgUI[3] = new MImage("ui3.png", "/uiuse.pkg", 12, 12);
        MainCanvas.mImgUI[4] = new MImage("ui4.png", "/uiuse.pkg", 38, 18);
        MainCanvas.mImgUI[5] = null;
        break;
      case 10:
        MainCanvas.mImgUI[6] = new MImage("ui6.png", "/uiuse.pkg", 104, 1);
        MainCanvas.mImgUI[7] = new MImage("ui7.png", "/uiuse.pkg", 1, 74);
        MainCanvas.mImageTitle = new MImage("uititle.png", "/uimenu.pkg", 240, 146);
        MainCanvas.mImageC = new MImage("uic.png", "/uimenu.pkg", 84, 9);
        break;
      case 11:
        MainCanvas.mImgUI[8] = new MImage("ui8.png", "/uiuse.pkg", 7, 6);
        MainCanvas.mImgUI[9] = new MImage("ui9.png", "/uiuse.pkg", 79, 24);
        MainCanvas.mImgUI[10] = new MImage("ui10.png", "/uiuse.pkg", 14, 14);
        MainCanvas.mImgUI[11] = new MImage("ui11.png", "/uiuse.pkg", 9, 9);
        MainCanvas.mImgUI[12] = new MImage("ui12.png", "/uiuse.pkg", 5, 7);
        MainCanvas.mImgUI[13] = new MImage("ui13.png", "/uiuse.pkg", 15, 12);
        break;
      case 14:
        MainCanvas.mImgUI[14] = new MImage("s.png", "/uiuse.pkg", 9, 9);
        MainCanvas.mImgUI[15] = new MImage("ui15.png", "/uiuse.pkg", 17, 17);
        MainCanvas.mImgUI[16] = null;
        MainCanvas.mImgUI[17] = new MImage("ui17.png", "/uiuse.pkg", 10, 9);
        MainCanvas.mImgUI[19] = new MImage("ui19.png", "/uiuse.pkg", 45, 35);
        break;
      case 16:
        getInstance().initCreatRes();
        break;
      case 17:
        MainCanvas.mImgUI[20] = new MImage("ui20.png", "/uiuse.pkg", 16, 16);
        MainCanvas.mImgUI[21] = new MImage("ui21.png", "/uiuse.pkg", 10, 10);
        MainCanvas.mImgUI[22] = new MImage("ui22.png", "/uiuse.pkg", 4, 5);
        MainCanvas.mImgUI[23] = new MImage("ui23.png", "/uiuse.pkg", 5, 4);
        MainCanvas.mImgUI[24] = new MImage("ui24.png", "/uiuse.pkg", 79, 24);
        MainCanvas.mImgUI[25] = new MImage("ui25.png", "/uiuse.pkg", 16, 16);
        break;
      case 19:
        MainCanvas.mImgUI[26] = new MImage("ui26.png", "/uiuse.pkg", 14, 14);
        MainCanvas.mImgUI[27] = new MImage("ui27.png", "/uiuse.pkg", 7, 7);
        MainCanvas.mImgUI[28] = new MImage("ui28.png", "/uiuse.pkg", 10, 10);
        MainCanvas.mImgUI[29] = new MImage("ui29.png", "/uiuse.pkg", 5, 5);
        break;
      case 22:
        MainCanvas.mImgUI[31] = new MImage("ui31.png", "/uiuse.pkg", 12, 12);
        MainCanvas.mImgUI[32] = new MImage("ui32.png", "/uiuse.pkg", 17, 16);
        MainCanvas.mImgUI[33] = new MImage("ui33.png", "/uiuse.pkg", 12, 12);
        MainCanvas.mImgUI[34] = new MImage("ui34.png", "/uiuse.pkg", 9, 7);
        MainCanvas.mImgUI[35] = new MImage("ui35.png", "/uiuse.pkg", 8, 15);
        MainCanvas.mImgUI[36] = new MImage("ui36.png", "/uiuse.pkg", 11, 11);
        MainCanvas.mImgUI[37] = new MImage("ui37.png", "/uiuse.pkg", 27, 10);
        break;
      case 24:
        MainCanvas.mImgUI[38] = new MImage("ui38.png", "/uiuse.pkg", 20, 18);
        MainCanvas.mImgUI[39] = new MImage("ui39.png", "/uiuse.pkg", 5, 5);
        MainCanvas.mImgStuff = new MImage("stuff.png", "/uiuse.pkg", 16, 16);
        MainCanvas.mImgSelect = new MImage("select.png", "/uiuse.pkg", 25, 15);
        MainCanvas.imgRedNum = new MImage("redNum.png", "/uiuse.pkg", 7, 10);
        MainCanvas.imgWhiteNum = new MImage("whiteNum.png", "/uiuse.pkg", 10, 9);
        break;
      case 26:
        MainCanvas.imgGreenNum = new MImage("greenNum.png", "/uiuse.pkg", 7, 10);
        MainCanvas.imgYellowNum = new MImage("yellowNum.png", "/uiuse.pkg", 13, 18);
        MainCanvas.imgPlayerArrow = new MImage("playerArrow.png", "/uiuse.pkg", 16, 21);
        MainCanvas.imgMarryArrow = new MImage("marry.png", "/uiuse.pkg", 14, 13);
        break;
      case 28:
        MainCanvas.mImageMenu = null;
        MainCanvas.mImageArrow = new MImage("uiarrow.png", "/uimenu.pkg", 11, 9);
        break;
      case 30:
        for (i = 0; i < MainCanvas.star.length; i++) {
          MainCanvas.star[i][0] = 5 + 
            Util.getRandom(MainCanvas.screenW - 10);
          MainCanvas.star[i][1] = 10 + Util.getRandom(20);
          MainCanvas.star[i][2] = Util.getRandom(4) + 2;
          MainCanvas.star[i][3] = MainCanvas.fireColor[Util.getRandom(4)];
          MainCanvas.star[i][4] = Util.getRandom(20);
        } 
        break;
      case 31:
        MainCanvas.dramatisPackage = new UIGrid(0, 113, (byte)4, (byte)9, (byte)4, MainCanvas.mImgStuff);
        break;
      case 32:
        if (PCIncrementService.isChargeJar) {
          if (PCIncrementService.hasAgree) {
            MainCanvas.mc.setState((byte)26);
            break;
          } 
          MainCanvas.mc.getSMS_Content(16);
          MainCanvas.mc.setState((byte)31);
          PCIncrementService.getInstance().getClass();
          (PCIncrementService.getInstance()).sendState = 3;
          break;
        } 
        MainCanvas.mc.setState((byte)26);
        break;
      case 33:
        MainCanvas.loadRequestCount = 0;
        break;
    } 
    MainCanvas.loadCount++;
    drawTJ2(g, MainCanvas.loadCount);
  }
  
  private void drawTJ2(Graphics g, int aWaitCnt) {
    g.setColor(11898450);
    g.drawString("天劫ONLINE为免费手机", 25, 80, 20);
    g.drawString("网络游戏,下载、安装不", 25, 110, 20);
    g.drawString("会有其他付费捆绑业务。", 25, 140, 20);
    g.drawString("载入中... " + (aWaitCnt * 3 + 1) + "%", MainCanvas.screenW >> 1, MainCanvas.screenH - 50, 17);
    int w = 72;
    int h = 6;
    g.setColor(8415039);
    g.drawRect(MainCanvas.screenW - w >> 1, MainCanvas.screenH - 30, 71, h - 1);
    g.setColor(2037253);
    g.fillRect((MainCanvas.screenW - w >> 1) + 2, MainCanvas.screenH - 28, w - 4, h - 4);
    g.setColor(15718814);
    g.fillRect((MainCanvas.screenW - w >> 1) + 2, MainCanvas.screenH - 28, (w - 4) * (aWaitCnt * 3 + 1) / 100, h - 4);
  }
  
  public static String loadingTip = null;
  
  String drawTip = "";
  
  String tempTip = "欢迎来到天劫！";
  
  private static final int COLOR_FLAG = 16711935;
  
  private void drawLoadingTip(Graphics g) {
    byte stringY = 120;
    g.setColor(15718815);
    String[] strs = null;
    if (loadingTip != null && !loadingTip.equals(""))
      this.tempTip = loadingTip; 
    strs = Util.wrapText(this.drawTip, MainCanvas.screenW - 30, MainCanvas.font[1]);
    byte lg = (byte)strs.length;
    byte i;
    for (i = 0; i < lg; i = (byte)(i + 1))
      g.drawString(strs[i], MainCanvas.screenW >> 1, stringY + i * (MainCanvas.CHARH + 5), 17); 
  }
  
  public void drawMenuPeople(Graphics g, int argX, int argY, byte argType) {
    short[][] frameDataPeople = (short[][])null;
    short[] picDatasPeople = null;
    Image tempImagePeople = null;
    short[][] motionDataAll = (short[][])null;
    switch (argType) {
      case 0:
        tempImagePeople = GameObj.imagePeopleWhiteCK;
        frameDataPeople = GameObj.frameDataPeopleWhiteCK;
        picDatasPeople = GameObj.picDatasPeopleWhiteCK;
        motionDataAll = GameObj.motionDataAllWhiteCK;
        break;
      case 1:
        tempImagePeople = GameObj.imagePeopleWhiteJK;
        frameDataPeople = GameObj.frameDataPeopleWhiteJK;
        picDatasPeople = GameObj.picDatasPeopleWhiteJK;
        motionDataAll = GameObj.motionDataAllWhiteJK;
        break;
      case 2:
        tempImagePeople = GameObj.imagePeopleWhiteDS;
        frameDataPeople = GameObj.frameDataPeopleWhiteDS;
        picDatasPeople = GameObj.picDatasPeopleWhiteDS;
        motionDataAll = GameObj.motionDataAllWhiteDS;
        break;
      case 3:
        tempImagePeople = GameObj.imagePeopleWhiteYS;
        frameDataPeople = GameObj.frameDataPeopleWhiteYS;
        picDatasPeople = GameObj.picDatasPeopleWhiteYS;
        motionDataAll = GameObj.motionDataAllWhiteYS;
        break;
      case 4:
        tempImagePeople = GameObj.imagePeopleBlackYS;
        frameDataPeople = GameObj.frameDataPeopleBlackYS;
        picDatasPeople = GameObj.picDatasPeopleBlackYS;
        motionDataAll = GameObj.motionDataAllBlackYS;
        break;
      case 5:
        tempImagePeople = GameObj.imagePeopleBlackCK;
        frameDataPeople = GameObj.frameDataPeopleBlackCK;
        picDatasPeople = GameObj.picDatasPeopleBlackCK;
        motionDataAll = GameObj.motionDataAllBlackCK;
        break;
      case 6:
        tempImagePeople = GameObj.imagePeopleBlackJK;
        frameDataPeople = GameObj.frameDataPeopleBlackJK;
        picDatasPeople = GameObj.picDatasPeopleBlackJK;
        motionDataAll = GameObj.motionDataAllBlackJK;
        break;
      case 7:
        tempImagePeople = GameObj.imagePeopleBlackDS;
        frameDataPeople = GameObj.frameDataPeopleBlackDS;
        picDatasPeople = GameObj.picDatasPeopleBlackDS;
        motionDataAll = GameObj.motionDataAllBlackDS;
        break;
    } 
    Util.drawRoleEditFrame(tempImagePeople, g, frameDataPeople[motionDataAll[0][0]], picDatasPeople, argX, argY, false);
  }
  
  public void drawCreat(Graphics g, int[] argX, int[] argY, boolean[] isMove, int maxN, byte[] imgId, boolean[] isDraw) {
    int moveIndex = 0;
    int standIndex = 0;
    int maxNum = maxN;
    for (int i = 0; i < maxNum; i++) {
      try {
        if (isDraw[i])
          if (isMove[i]) {
            moveIndex = 3;
            Util.drawRoleEditFrame(creatImage[imgId[i]], g, creatFrameData[imgId[i]][creatMotionDataAll[imgId[i]][moveIndex][creatFrameIndex]], creatPicData[imgId[i]], argX[i] + 18, argY[i] + 34, false);
            if (creatFrameIndex < (creatMotionDataAll[imgId[i]][moveIndex]).length - 1) {
              creatFrameIndex++;
            } else {
              creatFrameIndex = 0;
            } 
          } else {
            Util.drawRoleEditFrame(creatImage[imgId[i]], g, creatFrameData[imgId[i]][creatMotionDataAll[imgId[i]][standIndex][0]], creatPicData[imgId[i]], argX[i] + 18, argY[i] + 34, false);
          }  
      } catch (RuntimeException e) {
        e.printStackTrace();
      } 
    } 
  }
  
  public void initCreatRes() {
    if (!Util.isInitRes[6]) {
      creatImage = null;
      creatFrameData = (short[][][])null;
      creatPicData = (short[][])null;
      creatMotionDataAll = (short[][][])null;
      creatImage = new Image[8];
      creatFrameData = new short[8][][];
      creatPicData = new short[8][];
      creatMotionDataAll = new short[8][][];
      GameObj.initWhitePeopleRes();
      GameObj.initBlackPeopleRes();
      try {
        creatImage[0] = GameObj.imagePeopleWhiteCK;
        creatFrameData[0] = GameObj.frameDataPeopleWhiteCK;
        creatPicData[0] = GameObj.picDatasPeopleWhiteCK;
        creatMotionDataAll[0] = GameObj.motionDataAllWhiteCK;
        creatImage[1] = GameObj.imagePeopleWhiteJK;
        creatFrameData[1] = GameObj.frameDataPeopleWhiteJK;
        creatPicData[1] = GameObj.picDatasPeopleWhiteJK;
        creatMotionDataAll[1] = GameObj.motionDataAllWhiteJK;
        creatImage[2] = GameObj.imagePeopleWhiteDS;
        creatFrameData[2] = GameObj.frameDataPeopleWhiteDS;
        creatPicData[2] = GameObj.picDatasPeopleWhiteDS;
        creatMotionDataAll[2] = GameObj.motionDataAllWhiteDS;
        creatImage[3] = GameObj.imagePeopleWhiteYS;
        creatFrameData[3] = GameObj.frameDataPeopleWhiteYS;
        creatPicData[3] = GameObj.picDatasPeopleWhiteYS;
        creatMotionDataAll[3] = GameObj.motionDataAllWhiteYS;
        creatImage[4] = GameObj.imagePeopleBlackYS;
        creatFrameData[4] = GameObj.frameDataPeopleBlackYS;
        creatPicData[4] = GameObj.picDatasPeopleBlackYS;
        creatMotionDataAll[4] = GameObj.motionDataAllBlackYS;
        creatImage[5] = GameObj.imagePeopleBlackCK;
        creatFrameData[5] = GameObj.frameDataPeopleBlackCK;
        creatPicData[5] = GameObj.picDatasPeopleBlackCK;
        creatMotionDataAll[5] = GameObj.motionDataAllBlackCK;
        creatImage[6] = GameObj.imagePeopleBlackJK;
        creatFrameData[6] = GameObj.frameDataPeopleBlackJK;
        creatPicData[6] = GameObj.picDatasPeopleBlackJK;
        creatMotionDataAll[6] = GameObj.motionDataAllBlackJK;
        creatImage[7] = GameObj.imagePeopleBlackDS;
        creatFrameData[7] = GameObj.frameDataPeopleBlackDS;
        creatPicData[7] = GameObj.picDatasPeopleBlackDS;
        creatMotionDataAll[7] = GameObj.motionDataAllBlackDS;
      } catch (RuntimeException e) {
        e.printStackTrace();
      } 
      Util.isInitRes[6] = true;
    } 
  }
  
  public void drawSoundClew(Graphics g) {
    g.setColor(15718814);
    g.drawString("是否开启声音?", MainCanvas.screenW >> 1, (MainCanvas.screenH >> 1) - MainCanvas.CHARH, 17);
    g.drawString("打开", 3, MainCanvas.screenH - 3, 36);
    g.drawString("关闭", MainCanvas.screenW - 3, MainCanvas.screenH - 3, 40);
    keyInSoundClew();
  }
  
  public void keyInSoundClew() {
    if (MainCanvas.isKeyPress(17) || 
      !MainCanvas.isKeyPress(14));
    MainCanvas.mc.setState((byte)4);
  }
  
  private void drawFlags(Graphics g) {
    g.setColor(16711935);
    for (int i = 0; i < Map.FLAGS.length; i++) {
      int col = Map.FLAGS[i][0];
      int row = Map.FLAGS[i][1];
      if (col != -1 || row != -1)
        g.fillRect(getMapX(col, row) - 1, getMapY(col, row) - 1, 3, 3); 
    } 
  }
  
  public void drawTrumpetMessage(Graphics g) {
    if (PCChat.bTrumpetDrawType == 0)
      return; 
    if (PCChat.bTrumpetDrawType == 1) {
      g.setColor(0);
      int i, j;
      for (i = 0, j = PCChat.z_strTrumpetMessage.length; i < j; i++)
        g.drawString(PCChat.z_strTrumpetMessage[i], PCChat.nTrumpet_X + 1, PCChat.nTrumpet_Y + (MainCanvas.CHARH + 1) * i + 1, 0); 
      g.setColor(16777215);
      for (i = 0, j = PCChat.z_strTrumpetMessage.length; i < j; i++)
        g.drawString(PCChat.z_strTrumpetMessage[i], PCChat.nTrumpet_X, PCChat.nTrumpet_Y + (MainCanvas.CHARH + 1) * i, 0); 
    } else {
      g.setColor(0);
      g.drawString(PCChat.strTrumpetMessage, PCChat.nTrumpet_X + 1, PCChat.nTrumpet_Y + 1, 0);
      g.setColor(16777215);
      g.drawString(PCChat.strTrumpetMessage, PCChat.nTrumpet_X, PCChat.nTrumpet_Y, 0);
    } 
    PCChat.Logic_Trumpet();
  }
  
  public void drawTitleList(Graphics g) {
    if (MainCanvas.mc.baseForm == null) {
      MainCanvas.mc.titleTotalPage = MainCanvas.mc.titleTotal / 8;
      if (MainCanvas.mc.titleTotal % 8 != 0)
        MainCanvas.mc.titleTotalPage++; 
      String page = (MainCanvas.mc.titleCurrentPage + 1) + "/" + MainCanvas.mc.titleTotalPage;
      UILabel lblPage = new UILabel(0, 189, 0, 0, page, 15718814, (byte)1, (byte)0);
      MainCanvas.mc.baseForm = new UIForm(0, 0, (short)MainCanvas.screenW, (short)MainCanvas.screenH, "");
      UIRim frame = new UIRim(0, 0, (short)(MainCanvas.screenW - 1), (short)(MainCanvas.screenH - 1), (byte)4);
      UIRim rimTitle = new UIRim(0, 10, 160, 21, (byte)7);
      UILabel lblTitle = new UILabel(0, (short)(rimTitle.positionY + 2), 0, 0, "称号列表", 15718814, (byte)1, (byte)0);
      UIRim rimDown = new UIRim(0, 30, 160, 158, (byte)0);
      int tempcount = MainCanvas.mc.titleName.length;
      MainCanvas.mc.tables[0] = new UITable(0, 31, 160, 158, tempcount, 1, (tempcount > 8) ? 8 : tempcount, (byte)0, (byte)3);
      MainCanvas.mc.tables[0].setSingleWH((MainCanvas.mc.tables[0]).singleWidth, (byte)19, false);
      UILabel label1 = new UILabel(0, 0, 0, 0, "操作", 15718815, (byte)0, (byte)0);
      UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte)0, (byte)0);
      for (int i = 0; i < tempcount; i++) {
        if (MainCanvas.mc.titleName[i].equals(
            Player.getInstance().getTitle())) {
          MainCanvas.mc.tables[0].addItem(MainCanvas.mc.titleName[i] + "(使用中)", 15718815);
        } else {
          MainCanvas.mc.tables[0].addItem(MainCanvas.mc.titleName[i], 15718815);
        } 
      } 
      MainCanvas.mc.baseForm.addComponent(frame);
      MainCanvas.mc.baseForm.addComponentInCenter(rimDown, (byte)2);
      MainCanvas.mc.baseForm.addComponentInCenter(rimTitle, (byte)2);
      MainCanvas.mc.baseForm.addComponentInCenter(lblTitle, (byte)2);
      if (tempcount > 0)
        MainCanvas.mc.baseForm.addComponentInCenter(label1, (byte)5); 
      MainCanvas.mc.baseForm.addComponentInCenter(label2, (byte)6);
      MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.tables[0], (byte)2);
      MainCanvas.mc.baseForm.addComponentInCenter(lblPage, (byte)2);
      MainCanvas.mc.tables[0].setXY((MainCanvas.mc.tables[0]).positionX, (MainCanvas.mc.tables[0]).positionY);
      ClanWar.getInstance().drawFormArrow(MainCanvas.mc.baseForm, MainCanvas.mc.titleCurrentPage, MainCanvas.mc.titleTotalPage);
      MainCanvas.mc.tables[0].setOldFocus(true);
      MainCanvas.mc.baseForm.setFocus(true);
    } 
    MainCanvas.mc.baseForm.draw(g);
  }
  
  public void drawNPCTitleList(Graphics g) {
    if (MainCanvas.mc.baseForm == null) {
      MainCanvas.mc.baseForm = new UIForm(0, 0, (short)MainCanvas.screenW, (short)MainCanvas.screenH, "");
      UIRim frame = new UIRim(0, 0, (short)(MainCanvas.screenW - 1), (short)(MainCanvas.screenH - 1), (byte)4);
      UIRim rimTitle = new UIRim(0, 10, 160, 21, (byte)7);
      UILabel lblTitle = new UILabel(0, (short)(rimTitle.positionY + 2), 0, 0, "NPC称号列表", 15718814, (byte)1, (byte)0);
      UIRim rimDown = new UIRim(0, 30, 160, 158, (byte)0);
      int tempcount = MainCanvas.mc.titleName.length;
      MainCanvas.mc.tables[0] = new UITable(0, 31, 160, 158, tempcount, 1, (tempcount > 8) ? 8 : tempcount, (byte)0, (byte)3);
      MainCanvas.mc.tables[0].setAutoHeight(true);
      UILabel label1 = new UILabel(0, 0, 0, 0, "操作", 15718815, (byte)0, (byte)0);
      UILabel label2 = new UILabel(0, 0, 0, 0, "返回", 15718815, (byte)0, (byte)0);
      for (int i = 0; i < tempcount; i++)
        MainCanvas.mc.tables[0].addItem(MainCanvas.mc.titleName[i], 15718815); 
      MainCanvas.mc.baseForm.addComponent(frame);
      MainCanvas.mc.baseForm.addComponentInCenter(rimDown, (byte)2);
      MainCanvas.mc.baseForm.addComponentInCenter(rimTitle, (byte)2);
      MainCanvas.mc.baseForm.addComponentInCenter(lblTitle, (byte)2);
      if (tempcount > 0)
        MainCanvas.mc.baseForm.addComponentInCenter(label1, (byte)5); 
      MainCanvas.mc.baseForm.addComponentInCenter(label2, (byte)6);
      MainCanvas.mc.baseForm.addComponentInCenter(MainCanvas.mc.tables[0], (byte)2);
      MainCanvas.mc.tables[0].setXY((MainCanvas.mc.tables[0]).positionX, (MainCanvas.mc.tables[0]).positionY);
      MainCanvas.mc.baseForm.setFocus(true);
    } 
    MainCanvas.mc.baseForm.draw(g);
  }
}
