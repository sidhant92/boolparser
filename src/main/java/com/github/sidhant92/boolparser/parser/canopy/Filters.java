package com.github.sidhant92.boolparser.parser.canopy;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Filters extends Grammar {
    public Filters(String input, Actions actions) {
        this.input = input;
        this.inputSize = input.length();
        this.actions = actions;
        this.offset = 0;
        this.cache = new EnumMap<Label, Map<Integer, CacheRecord>>(Label.class);
        this.failure = 0;
        this.expected = new ArrayList<String>();
    }

    public static TreeNode parse(String input, Actions actions) throws ParseError {
        Filters parser = new Filters(input, actions);
        return parser.parse();
    }

    public static TreeNode parse(String input) throws ParseError {
        return parse(input, null);
    }

    private static String formatError(String input, int offset, List<String> expected) {
        String[] lines = input.split("\n");
        int lineNo = 0, position = 0;
        while (position <= offset) {
            position += lines[lineNo].length() + 1;
            lineNo += 1;
        }
        String message = "Line " + lineNo + ": expected " + expected + "\n";
        String line = lines[lineNo - 1];
        message += line + "\n";
        position -= line.length() + 1;
        while (position < offset) {
            message += " ";
            position += 1;
        }
        return message + "^";
    }

    private TreeNode parse() throws ParseError {
        TreeNode tree = _read_start();
        if (tree != FAILURE && offset == inputSize) {
            return tree;
        }
        if (expected.isEmpty()) {
            failure = offset;
            expected.add("<EOF>");
        }
        throw new ParseError(formatError(input, failure, expected));
    }
}
