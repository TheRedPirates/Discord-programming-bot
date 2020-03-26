public class SyntaxCommands {
    private static String printPrefix = "```java\n";
    private static String printPostfix = "\n```";

    static String printIf() {
        return printPrefix + "if (condition) {\n    // Write your code here\n} else {\n    // Write your code here\n}" + printPostfix;
    }

    static String printFor() {
        return printPrefix + "for (int i = 0; i < n; i++) {\n    // Write your code here\n}" + printPostfix;
    }

    static String printWhile() {
        return printPrefix + "while (i < n) {\n    // Write your code here\n}" + printPostfix;
    }

    static String printSwitch() {
        return printPrefix + "switch (key) {\n    case value:\n\t// Write your code here\n\tbreak;\n\n    default:\n\t// Write your code here\n\tbreak;\n}" + printPostfix;
    }

    static String printFunction() {
        return printPrefix + "public/protected/private [ static ] void/return value funcName (type param1, type param2, ...) {\n    // Write your code here\n}" + printPostfix;
    }

    static String printClass() {
        return printPrefix + "public class ClassName {\n    public/protected/private [ static ] type param;\n\n    public ClassName() {\n\t// Write your contructor code here\n    }\n}" + printPostfix;
    }
}