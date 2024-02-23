package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {
    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        List<String> parts = StringSplitter.splitByDelimiters(signatureString, List.of(" ", "(", ")", ","));
        int startIndex = 0;
        String accessModifier = null;
        if (isAccessModifier(parts.get(0))) {
            accessModifier = parts.get(0);
            startIndex = 1;
        }

        String returnType = parts.get(startIndex);
        String methodName = parts.get(startIndex+1);

        List<MethodSignature.Argument> argumentList = new ArrayList<>();

        for (int i = startIndex + 2; i < parts.size(); i += 2) {
            String argumentType = parts.get(i);
            String argumentName = parts.get(i + 1);
            argumentList.add(new MethodSignature.Argument(argumentType, argumentName));
        }
        MethodSignature ret = new MethodSignature(methodName, argumentList);
        ret.setAccessModifier(accessModifier);
        ret.setReturnType(returnType);
        return ret;
    }
    private boolean isAccessModifier(String token) {
        return token.equals("public") || token.equals("private") || token.equals("protected");
    }
}
