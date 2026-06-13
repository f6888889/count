import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String[] studentNames = {
                "朱宝贺", "梁文俊", "蓝小飞", "秦铭友", "黄彩霞",
                "赵敬宜", "张舒琪", "姚松秀", "张晨芮", "刘瑛璐",
                "信喆", "黄文杰", "马丽琪", "常朕豪", "赵正阳",
                "李永慷", "姚泽灿", "付峻宇", "王家欢", "梁真",
                "唐光翔", "吴家永", "吴雨桂", "邹礼琪", "邵光宇",
                "孔维炜", "梁志锋", "禤晓晓", "莫如婷", "冯瑞",
                "钟一邵", "陈思尧", "阮福成", "李家铭", "杨勇武",
                "郝韵洁", "王和垂"
        };

        String[] folderPaths = {
                "E:\\M 学委专用\\二下\\计算机组成原理\\软工2401班-实验01",
                "E:\\M 学委专用\\二下\\计算机组成原理\\软工2401班-实验02",
                "E:\\M 学委专用\\二下\\计算机组成原理\\软工2401班-实验03",
                "E:\\M 学委专用\\二下\\计算机组成原理\\软工2401班-实验04"
        };

        System.out.println("========== 多文件夹作业提交检查 ==========");

        for (String folderPath : folderPaths) {
            checkSubmissions(folderPath, studentNames);
            System.out.println("=".repeat(60));
        }

        System.out.println("\n记得交以上作业，自己看一下，发了给我但是显示未交的跟我说一声。");
    }

    private static void checkSubmissions(String folderPath, String[] studentNames) {
        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("   警告：文件夹路径不存在或无效 -> " + folderPath);
            return;
        }

        File[] files = folder.listFiles();
        List<String> existingFileNames = new ArrayList<>();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    existingFileNames.add(file.getName());
                }
            }
        }

        String folderName = extractFolderName(folderPath);

        System.out.println("【" + folderName + "】");

        List<String> submitted = new ArrayList<>();
        List<String> notSubmitted = new ArrayList<>();

        for (String studentName : studentNames) {
            boolean isFound = false;
            for (String fileName : existingFileNames) {
                if (fileName.contains(studentName)) {
                    isFound = true;
                    break;
                }
            }

            if (isFound) {
                submitted.add(studentName);
            } else {
                notSubmitted.add(studentName);
            }
        }

        System.out.println("【已交】共 " + submitted.size() + " 人：");
        if (submitted.isEmpty()) {
            System.out.println("  （无）");
        } else {
            printNamesInRows(submitted, 5);
        }

        System.out.println("【未交】共 " + notSubmitted.size() + " 人：");
        if (notSubmitted.isEmpty()) {
            System.out.println("  （无）");
        } else {
            printNamesInRows(notSubmitted, 5);
        }
    }

    private static String extractFolderName(String folderPath) {
        File folder = new File(folderPath);
        String folderName = folder.getName();

        StringBuilder result = new StringBuilder();
        for (char c : folderName.toCharArray()) {
            if (Character.isLetterOrDigit(c) || (c >= '\u4e00' && c <= '\u9fa5')) {
                result.append(c);
            }
        }

        return result.length() > 0 ? result.toString() : folderName;
    }

    private static void printNamesInRows(List<String> names, int namesPerRow) {
        for (int i = 0; i < names.size(); i++) {
            System.out.print("   " + names.get(i));
            if ((i + 1) % namesPerRow == 0 || i == names.size() - 1) {
                System.out.println();
            }
        }
    }
}
