public class OtherPlayer extends GameObj {
    public OtherPlayer() {
        type = 1;
    }

    public void init(ByteArray initIn) {
        curStep = 0;
        objID = initIn.readInt();
        x = initIn.readInt();
        y = initIn.readInt();
        imgID = initIn.readByte();
        originalImgID = imgID;
        initData();
        isPlayerWife = initIn.readByte();
        int tempWeapon = initIn.readByte();
        originalWeapon = tempWeapon;
        if (currentImgID == -1) {
            setCurWeapon(tempWeapon);
        }

        level = initIn.readByte();
        name = initIn.readUTF();
        maxHp = initIn.readInt();
        lastHp = curHp = initIn.readInt();
        maxMp = initIn.readInt();
        curMp = initIn.readInt();
        col = Map.getCurrentColumn(y, x);
        row = Map.getCurrentRow(y, x);
        setDirection((byte)2);
        setAimRowAndColumn(row, col);
        Map.putInCell(col, row);
        setState((byte)0);
        initEditorRes(imgID);
        if (imgID <= 3) {
            group = 1;
        } else {
            group = 2;
        }

    }

    public void initData() {
        if (imgID >= 0 && imgID <= Cons.PLAYERS.length) {
            race = Cons.PLAYERS[imgID][0];
            profession = Cons.PLAYERS[imgID][1];
            gender = Cons.PLAYERS[imgID][2];
        }
    }

    public void tick() {
        super.tick();
        switch (state) {
            case 1:
                findPath();
            default:
                nextFrame();
                Map.putInCell(col, row);
        }
    }

    public void initEditorRes(int argID) {
        switch (argID) {
            case 0:
                picDatasPeople = GameObj.picDatasPeopleWhiteCK;
                tempImagePeople = GameObj.imagePeopleWhiteCK;
                frameDataPeople = GameObj.frameDataPeopleWhiteCK;
                motionDataAll = GameObj.motionDataAllWhiteCK;
                frameDataSpecific = GameObj.frameDataSpecificWhiteCK;
                picDatasSpecific = GameObj.picDatasSpecificCK;
                testImageSpecific = GameObj.imageSpecificCK;
                break;
            case 1:
                picDatasPeople = GameObj.picDatasPeopleWhiteJK;
                tempImagePeople = GameObj.imagePeopleWhiteJK;
                frameDataPeople = GameObj.frameDataPeopleWhiteJK;
                motionDataAll = GameObj.motionDataAllWhiteJK;
                frameDataSpecific = GameObj.frameDataSpecificWhiteJK;
                picDatasSpecific = GameObj.picDatasSpecificCK;
                testImageSpecific = GameObj.imageSpecificCK;
                break;
            case 2:
                picDatasPeople = GameObj.picDatasPeopleWhiteDS;
                tempImagePeople = GameObj.imagePeopleWhiteDS;
                frameDataPeople = GameObj.frameDataPeopleWhiteDS;
                motionDataAll = GameObj.motionDataAllWhiteDS;
                frameDataSpecific = GameObj.frameDataSpecificWhiteDS;
                picDatasSpecific = GameObj.picDatasSpecificCK;
                testImageSpecific = GameObj.imageSpecificCK;
                break;
            case 3:
                picDatasPeople = GameObj.picDatasPeopleWhiteYS;
                tempImagePeople = GameObj.imagePeopleWhiteYS;
                frameDataPeople = GameObj.frameDataPeopleWhiteYS;
                motionDataAll = GameObj.motionDataAllWhiteYS;
                frameDataSpecific = GameObj.frameDataSpecificWhiteYS;
                picDatasSpecific = GameObj.picDatasSpecificCK;
                testImageSpecific = GameObj.imageSpecificCK;
                break;
            case 4:
                picDatasPeople = GameObj.picDatasPeopleBlackYS;
                tempImagePeople = GameObj.imagePeopleBlackYS;
                frameDataPeople = GameObj.frameDataPeopleBlackYS;
                motionDataAll = GameObj.motionDataAllBlackYS;
                frameDataSpecific = GameObj.frameDataSpecificBlackYS;
                picDatasSpecific = GameObj.picDatasSpecificCK;
                testImageSpecific = GameObj.imageSpecificCK;
                break;
            case 5:
                picDatasPeople = GameObj.picDatasPeopleBlackCK;
                tempImagePeople = GameObj.imagePeopleBlackCK;
                frameDataPeople = GameObj.frameDataPeopleBlackCK;
                motionDataAll = GameObj.motionDataAllBlackCK;
                frameDataSpecific = GameObj.frameDataSpecificBlackCK;
                picDatasSpecific = GameObj.picDatasSpecificCK;
                testImageSpecific = GameObj.imageSpecificCK;
                break;
            case 6:
                picDatasPeople = GameObj.picDatasPeopleBlackJK;
                tempImagePeople = GameObj.imagePeopleBlackJK;
                frameDataPeople = GameObj.frameDataPeopleBlackJK;
                motionDataAll = GameObj.motionDataAllBlackJK;
                frameDataSpecific = GameObj.frameDataSpecificBlackJK;
                picDatasSpecific = GameObj.picDatasSpecificCK;
                testImageSpecific = GameObj.imageSpecificCK;
                break;
            case 7:
                picDatasPeople = GameObj.picDatasPeopleBlackDS;
                tempImagePeople = GameObj.imagePeopleBlackDS;
                frameDataPeople = GameObj.frameDataPeopleBlackDS;
                motionDataAll = GameObj.motionDataAllBlackDS;
                frameDataSpecific = GameObj.frameDataSpecificBlackDS;
                picDatasSpecific = GameObj.picDatasSpecificCK;
                testImageSpecific = GameObj.imageSpecificCK;
        }

    }
}
