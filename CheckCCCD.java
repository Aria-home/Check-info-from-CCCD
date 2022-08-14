import java.util.*;

public class Asm01 {
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    public static void showMainMenu(String AUTHOR, String VERSION) {
        System.out.println("+----------+-------------------------+----------+");
        System.out.println("| NGAN HANG SO | " + AUTHOR + "@" + VERSION + "\t\t\t\t\t|");
        System.out.println("+----------+-------------------------+----------+");
        System.out.println("| 1. Nhap CCCD\t\t\t\t\t\t\t\t\t|");
        System.out.println("| 0. Thoat\t\t\t\t\t\t\t\t\t\t|");
        System.out.println("+----------+-------------------------+----------+");
        System.out.print("Chuc nang: ");
    }

    public static void showEasyHardOption() {
        System.out.println("Hay chon do kho cua Ma xac nhan: ");
        System.out.println("\t| 1. De");
        System.out.println("\t| 2. Kho");
        System.out.print("Do kho: ");
    }

    public static void searchMenu() {
        System.out.println("\t| 1. Kiem tra noi sinh");
        System.out.println("\t| 2. Kiem tra tuoi, gioi tinh");
        System.out.println("\t| 3. Kiem tra so ngau nhien");
        System.out.println("\t| 0. Thoat");
        System.out.print("Chuc nang: ");
    }

    public static void catchHandle() {
        System.out.print("Gia tri khong hop le. Hay nhap lai: ");
        scanner.nextLine(); //reset value saved in scanner to prevent infinity loop
    }

    public static boolean validateOption(byte option, boolean isExit) {
        if (option == 0) isExit = true;
        else System.out.print("Chuc nang khong ton tai! Vui long nhap lai chuc nang: ");
        return isExit;
    }

    public static int randomEasyNo() {
        int verificationCode = (random.nextInt(900) + 100);
        System.out.println("Nhap ma xac thuc: " + verificationCode);
        return verificationCode;
    }

    public static String randomAlphaNumeric() {
        String hardCString;
        String pattern = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6}$";
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        //keep generate if not match pattern (at least 1 one digit, 1 uppercase)
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 6; i++) {
                int randomIndex = random.nextInt(chars.length());
                sb.append(chars.charAt(randomIndex));
            }
            hardCString = sb.toString();
        } while (!hardCString.matches(pattern));
        scanner.nextLine(); //reset scanner

        System.out.println("Nhap ma xac thuc: " + hardCString);
        return hardCString;
    }

    public static void verifyEasyCode(int validCode) {
        while (true) {
            //prevent crash
            try {
                int reconfirm = scanner.nextInt();
                if (reconfirm == validCode) {
                    System.out.print("Vui long nhap so CCCD: ");
                    scanner.nextLine(); //reset scanner for next input CCCD
                    break;
                } else {
                    System.out.print("Ma xac thuc khong dung. Vui long thu lai: ");
                }
            }
            //Error message if detect string input
            catch (InputMismatchException e) {
                System.out.print("Gia tri khong hop le. Hay nhap lai: ");
            }
            scanner.nextLine(); //reset value saved in scanner to prevent infinity loop
        }
    }

    public static void verifyHardString(String hardString) {
        while (true) {
            String reconfirm = scanner.nextLine();
            if (reconfirm.equals(hardString)) {
                System.out.print("Vui long nhap so CCCD: ");
                break;
            } else {
                System.out.print("Ma xac thuc khong dung. Vui long thu lai: ");
            }
        }
    }

    public static String getCCCD() {
        String valueOfCCCD;
        while (true) {
            valueOfCCCD = scanner.nextLine().trim();
            //prevent crash
            try {
                if (valueOfCCCD.length() == 12) {
                    Long.parseLong(valueOfCCCD);
                    return valueOfCCCD;
                } else if (valueOfCCCD.equals("No")) {
                    valueOfCCCD = "";
                    return valueOfCCCD;
                } else {
                    System.out.print("So CCCD khong hop le.\nVui long nhap lai hoac 'No' de thoat: ");
                }
            }
            //Error if not contain only numbers (cannot parse to number)
            catch (NumberFormatException e) {
                System.out.print("So CCCD khong hop le.\nVui long nhap lai hoac 'No' de thoat: ");
            }
        }
    }

    public static void showPlaceOfBirth(String inputtedCCCD,
                                        ArrayList<String> provinceCodeCollection,
                                        ArrayList<String> placeOfBirthCollection) {
        //Separate province code from CCCD (first 3 no.)
        String provinceCode = inputtedCCCD.substring(0, 3);
        try {
            int index = provinceCodeCollection.indexOf(provinceCode);
            String placeOfBirth = placeOfBirthCollection.get(index);
            System.out.println("Noi sinh: " + placeOfBirth);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Noi sinh: Khong xac dinh");
        }
        searchMenu();
    }

    public static void showGenderAndAge(String inputtedCCCD) {
        //Separate gender code from CCCD (next 1 no.) and age code (next 2 no.)
        int genderCode = Integer.parseInt(inputtedCCCD.substring(3, 4));
        String ageCode = inputtedCCCD.substring(4, 6);
        String gender;
        String year;

        //check condition "Nam": 0,2,4,6,8
        if (genderCode % 2 == 0) {
            gender = "Nam";
        } else gender = "Nu";

        switch (genderCode) {
            case 0, 1 -> year = "19";
            case 2, 3 -> year = "20";
            case 4, 5 -> year = "21";
            case 6, 7 -> year = "22";
            case 8, 9 -> year = "23";
            default -> year = "";
        }
        System.out.println("Gioi tinh: " + gender + " | " + year + ageCode);
        searchMenu();
    }

    public static void main(String[] args) {
        final String AUTHOR = "FX16325";
        final String VERSION = "v1.0.0";
        boolean isExit = false;
        boolean isExitAll = false;
        byte option;
        String inputtedCCCD;

        String[] placeOfBirthArray = {"Ha Noi", "Ha Giang", "Cao Bang", "Bac Kan",
                "Tuyen Quang", "Lao Cai", "Dien Bien", "Lai Chau",
                "Son La", "Yen Bai", "Hoa Binh", "Thai Nguyen",
                "Lang Son", "Quang Ninh", "Bac Giang", "Phu Tho",
                "Vinh Phuc", "Bac Ninh", "Hai Duong", "Hai Phong",
                "Hung Yen", "Thai Binh", "Ha Nam", "Nam Dinh",
                "Ninh Binh", "Thanh Hoa", "Nghe An", "Ha Tinh",
                "Quang Binh", "Quang Tri", "Thua Thien Hue",
                "Da Nang", "Quang Nam", "Quang Ngai", "Binh Dinh",
                "Phu Yen", "Khanh Hoa", "Ninh Thuan", "Binh Thuan",
                "Kon Tum", "Gia Lai", "Dak Lak", "Dak Nong", "Lam Dong",
                "Binh Phuoc", "Tay Ninh", "Binh Duong", "Dong Nai",
                "Ba Ria - Vung Tau", "Ho Chi Minh", "Long An",
                "Tien Giang", "Ben Tre", "Tra Vinh", "Vinh Long",
                "Dong Thap", "An Giang", "Kien Giang", "Can Tho",
                "Hau Giang", "Soc Trang", "Bac Lieu", "Ca Mau"};

        String[] locationCodeArr = {"001", "002", "004", "006", "008", "010", "011",
                "012", "014", "015", "017", "019", "020", "022", "024", "025", "026",
                "027", "030", "031", "033", "034", "035", "036", "037", "038", "040",
                "042", "044", "045", "046", "048", "049", "051", "052", "054", "056",
                "058", "060", "062", "064", "066", "067", "068", "070", "072", "074",
                "075", "077", "079", "080", "082", "083", "084", "086", "087", "089",
                "091", "092", "093", "094", "095", "096"};

        ArrayList<String> placeOfBirthCollection = new ArrayList<>();
        Collections.addAll(placeOfBirthCollection, placeOfBirthArray);

        ArrayList<String> provinceCodeCollection = new ArrayList<>();
        Collections.addAll(provinceCodeCollection, locationCodeArr);

        while (!isExitAll) {
            //Show Main Menu
            showMainMenu(AUTHOR, VERSION);

            //Check option chose from Main Menu
            while (true) {
                //Prevent crash if input string
                try {
                    option = scanner.nextByte();
                    if (option == 1) {
                        showEasyHardOption();
                        break;
                    }
                    else {
                        //Validate if valid option
                        isExitAll = validateOption(option, isExit);
                        if (isExitAll) {
                            //Exit all loops if user chose O. Thoat
                            System.out.println("Ban da thoat. Cam on ban da su dung chuong trinh!");
                            break;
                        }
                    }
                }
                //Show message if detect string input
                catch (InputMismatchException e) {
                    catchHandle();
                }
            }

            //Continue if option value is 1. Nhap CCCD
            if (option == 1) {
                //Confirm option for Ma xac thuc is Easy or Hard
                while (true) {
                    //prevent crash if input string
                    try {
                        option = scanner.nextByte();
                        if (option == 1) {
                            //Generate and confirm easy string code
                            int validCode = randomEasyNo();
                            verifyEasyCode(validCode);
                            break;
                        } else if (option == 2) {
                            //Generate and confirm hard string code
                            String hardString = randomAlphaNumeric();
                            verifyHardString(hardString);
                            break;
                        } else {
                            //Ask user input again
                            System.out.print("Do kho khong ton tai. Hay nhap lai: ");
                        }
                    }
                    //Show message if detect string input
                    catch (InputMismatchException e) {
                        catchHandle();
                    }
                }

                //Check input CCCD after verified Ma xac thuc
                inputtedCCCD = getCCCD();


                //Continue after verified CCCD
                if (inputtedCCCD.length() == 12) {
                    //Show search options
                    searchMenu();
                    while (true) {
                        //prevent crash
                        try {
                            option = scanner.nextByte();
                            if (option == 1) {
                                showPlaceOfBirth(inputtedCCCD, provinceCodeCollection, placeOfBirthCollection);
                            } else if (option == 2) {
                                showGenderAndAge(inputtedCCCD);
                            } else if (option == 3) {
                                // show random no (last 6 no.)
                                System.out.println("So ngau nhien: " + inputtedCCCD.substring(6));
                                searchMenu();
                            }
                            //Back to Main menu if use chose 0 else ask again for valid option
                            else if (validateOption(option, isExit)) break;
                        }
                        //Show message if detect string input
                        catch (InputMismatchException e) {
                            System.out.print("Gia tri khong hop le. Hay nhap lai: ");
                            scanner.nextLine(); //reset value saved in scanner to prevent infinity loop
                        }
                    }
                }
                else {
                    //Exit program as user choose No to exit when verify CCCD
                    System.out.println("Ban da thoat. Chuc ban mot ngay tot lanh!");
                    break;
                }
            }
        }

        //Close Scanner class
        scanner.close();
    }
}


