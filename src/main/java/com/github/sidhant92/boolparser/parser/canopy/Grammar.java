package com.github.sidhant92.boolparser.parser.canopy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

abstract class Grammar {
    static TreeNode FAILURE = new TreeNode();

    int inputSize, offset, failure;
    String input;
    List<String> expected;
    Map<Label, Map<Integer, CacheRecord>> cache;
    Actions actions;

    private static Pattern REGEX_1 = Pattern.compile("\\A[0-9]");
    private static Pattern REGEX_2 = Pattern.compile("\\A[0-9]");
    private static Pattern REGEX_3 = Pattern.compile("\\A[0-9]");
    private static Pattern REGEX_4 = Pattern.compile("\\A[a-zA-Z0-9_.]");
    private static Pattern REGEX_5 = Pattern.compile("\\A[\"]");
    private static Pattern REGEX_6 = Pattern.compile("\\A[a-zA-Z0-9_.' \\t]");
    private static Pattern REGEX_7 = Pattern.compile("\\A[\"]");
    private static Pattern REGEX_8 = Pattern.compile("\\A[']");
    private static Pattern REGEX_9 = Pattern.compile("\\A[a-zA-Z0-9_.\" \\t]");
    private static Pattern REGEX_10 = Pattern.compile("\\A[']");
    private static Pattern REGEX_11 = Pattern.compile("\\A[0-9]");
    private static Pattern REGEX_12 = Pattern.compile("\\A[0-9]");
    private static Pattern REGEX_13 = Pattern.compile("\\A[ \\t]");

    TreeNode _read_start() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.start);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.start, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            address0 = _read_logical_or();
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_logical_or() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.logical_or);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.logical_or, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            address1 = _read_logical_and();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int remaining0 = 0;
                int index2 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>();
                TreeNode address3 = new TreeNode("", -1);
                while (address3 != FAILURE) {
                    int index3 = offset;
                    List<TreeNode> elements2 = new ArrayList<TreeNode>(4);
                    TreeNode address4 = FAILURE;
                    int remaining1 = 1;
                    int index4 = offset;
                    List<TreeNode> elements3 = new ArrayList<TreeNode>();
                    TreeNode address5 = new TreeNode("", -1);
                    while (address5 != FAILURE) {
                        address5 = _read_ws();
                        if (address5 != FAILURE) {
                            elements3.add(address5);
                            --remaining1;
                        }
                    }
                    if (remaining1 <= 0) {
                        address4 = new TreeNode(input.substring(index4, offset), index4, elements3);
                        offset = offset;
                    } else {
                        address4 = FAILURE;
                    }
                    if (address4 != FAILURE) {
                        elements2.add(0, address4);
                        TreeNode address6 = FAILURE;
                        address6 = _read_or();
                        if (address6 != FAILURE) {
                            elements2.add(1, address6);
                            TreeNode address7 = FAILURE;
                            int remaining2 = 1;
                            int index5 = offset;
                            List<TreeNode> elements4 = new ArrayList<TreeNode>();
                            TreeNode address8 = new TreeNode("", -1);
                            while (address8 != FAILURE) {
                                address8 = _read_ws();
                                if (address8 != FAILURE) {
                                    elements4.add(address8);
                                    --remaining2;
                                }
                            }
                            if (remaining2 <= 0) {
                                address7 = new TreeNode(input.substring(index5, offset), index5, elements4);
                                offset = offset;
                            } else {
                                address7 = FAILURE;
                            }
                            if (address7 != FAILURE) {
                                elements2.add(2, address7);
                                TreeNode address9 = FAILURE;
                                address9 = _read_logical_and();
                                if (address9 != FAILURE) {
                                    elements2.add(3, address9);
                                } else {
                                    elements2 = null;
                                    offset = index3;
                                }
                            } else {
                                elements2 = null;
                                offset = index3;
                            }
                        } else {
                            elements2 = null;
                            offset = index3;
                        }
                    } else {
                        elements2 = null;
                        offset = index3;
                    }
                    if (elements2 == null) {
                        address3 = FAILURE;
                    } else {
                        address3 = new TreeNode2(input.substring(index3, offset), index3, elements2);
                        offset = offset;
                    }
                    if (address3 != FAILURE) {
                        elements1.add(address3);
                        --remaining0;
                    }
                }
                if (remaining0 <= 0) {
                    address2 = new TreeNode(input.substring(index2, offset), index2, elements1);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.make_logical_or(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_logical_and() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.logical_and);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.logical_and, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            address1 = _read_primary();
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int remaining0 = 0;
                int index2 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>();
                TreeNode address3 = new TreeNode("", -1);
                while (address3 != FAILURE) {
                    int index3 = offset;
                    List<TreeNode> elements2 = new ArrayList<TreeNode>(4);
                    TreeNode address4 = FAILURE;
                    int remaining1 = 1;
                    int index4 = offset;
                    List<TreeNode> elements3 = new ArrayList<TreeNode>();
                    TreeNode address5 = new TreeNode("", -1);
                    while (address5 != FAILURE) {
                        address5 = _read_ws();
                        if (address5 != FAILURE) {
                            elements3.add(address5);
                            --remaining1;
                        }
                    }
                    if (remaining1 <= 0) {
                        address4 = new TreeNode(input.substring(index4, offset), index4, elements3);
                        offset = offset;
                    } else {
                        address4 = FAILURE;
                    }
                    if (address4 != FAILURE) {
                        elements2.add(0, address4);
                        TreeNode address6 = FAILURE;
                        address6 = _read_and();
                        if (address6 != FAILURE) {
                            elements2.add(1, address6);
                            TreeNode address7 = FAILURE;
                            int remaining2 = 1;
                            int index5 = offset;
                            List<TreeNode> elements4 = new ArrayList<TreeNode>();
                            TreeNode address8 = new TreeNode("", -1);
                            while (address8 != FAILURE) {
                                address8 = _read_ws();
                                if (address8 != FAILURE) {
                                    elements4.add(address8);
                                    --remaining2;
                                }
                            }
                            if (remaining2 <= 0) {
                                address7 = new TreeNode(input.substring(index5, offset), index5, elements4);
                                offset = offset;
                            } else {
                                address7 = FAILURE;
                            }
                            if (address7 != FAILURE) {
                                elements2.add(2, address7);
                                TreeNode address9 = FAILURE;
                                address9 = _read_primary();
                                if (address9 != FAILURE) {
                                    elements2.add(3, address9);
                                } else {
                                    elements2 = null;
                                    offset = index3;
                                }
                            } else {
                                elements2 = null;
                                offset = index3;
                            }
                        } else {
                            elements2 = null;
                            offset = index3;
                        }
                    } else {
                        elements2 = null;
                        offset = index3;
                    }
                    if (elements2 == null) {
                        address3 = FAILURE;
                    } else {
                        address3 = new TreeNode4(input.substring(index3, offset), index3, elements2);
                        offset = offset;
                    }
                    if (address3 != FAILURE) {
                        elements1.add(address3);
                        --remaining0;
                    }
                }
                if (remaining0 <= 0) {
                    address2 = new TreeNode(input.substring(index2, offset), index2, elements1);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.make_logical_and(input, index1, offset, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_primary() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.primary);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.primary, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            address0 = _read_token();
            if (address0 == FAILURE) {
                offset = index1;
                int index2 = offset;
                List<TreeNode> elements0 = new ArrayList<TreeNode>(3);
                TreeNode address1 = FAILURE;
                String chunk0 = null;
                if (offset < inputSize) {
                    chunk0 = input.substring(offset, Math.min(offset + 1, input.length()));
                }
                if (chunk0 != null && chunk0.equals("(")) {
                    address1 = new TreeNode(input.substring(offset, offset + 1), offset);
                    offset = offset + 1;
                } else {
                    address1 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String>();
                    }
                    if (offset == failure) {
                        expected.add("\"(\"");
                    }
                }
                if (address1 != FAILURE) {
                    elements0.add(0, address1);
                    TreeNode address2 = FAILURE;
                    address2 = _read_logical_or();
                    if (address2 != FAILURE) {
                        elements0.add(1, address2);
                        TreeNode address3 = FAILURE;
                        String chunk1 = null;
                        if (offset < inputSize) {
                            chunk1 = input.substring(offset, Math.min(offset + 1, input.length()));
                        }
                        if (chunk1 != null && chunk1.equals(")")) {
                            address3 = new TreeNode(input.substring(offset, offset + 1), offset);
                            offset = offset + 1;
                        } else {
                            address3 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String>();
                            }
                            if (offset == failure) {
                                expected.add("\")\"");
                            }
                        }
                        if (address3 != FAILURE) {
                            elements0.add(2, address3);
                        } else {
                            elements0 = null;
                            offset = index2;
                        }
                    } else {
                        elements0 = null;
                        offset = index2;
                    }
                } else {
                    elements0 = null;
                    offset = index2;
                }
                if (elements0 == null) {
                    address0 = FAILURE;
                } else {
                    address0 = actions.make_primary(input, index2, offset, elements0);
                    offset = offset;
                }
                if (address0 == FAILURE) {
                    offset = index1;
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_token() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.token);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.token, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            int index2 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(7);
            TreeNode address1 = FAILURE;
            int index3 = offset;
            String chunk0 = null;
            if (offset < inputSize) {
                chunk0 = input.substring(offset, Math.min(offset + 3, input.length()));
            }
            if (chunk0 != null && chunk0.equals("NOT")) {
                address1 = new TreeNode(input.substring(offset, offset + 3), offset);
                offset = offset + 3;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String>();
                }
                if (offset == failure) {
                    expected.add("\"NOT\"");
                }
            }
            if (address1 == FAILURE) {
                address1 = new TreeNode(input.substring(index3, index3), index3);
                offset = index3;
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int remaining0 = 0;
                int index4 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>();
                TreeNode address3 = new TreeNode("", -1);
                while (address3 != FAILURE) {
                    address3 = _read_ws();
                    if (address3 != FAILURE) {
                        elements1.add(address3);
                        --remaining0;
                    }
                }
                if (remaining0 <= 0) {
                    address2 = new TreeNode(input.substring(index4, offset), index4, elements1);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                    TreeNode address4 = FAILURE;
                    address4 = _read_alphanumeric();
                    if (address4 != FAILURE) {
                        elements0.add(2, address4);
                        TreeNode address5 = FAILURE;
                        int remaining1 = 0;
                        int index5 = offset;
                        List<TreeNode> elements2 = new ArrayList<TreeNode>();
                        TreeNode address6 = new TreeNode("", -1);
                        while (address6 != FAILURE) {
                            address6 = _read_ws();
                            if (address6 != FAILURE) {
                                elements2.add(address6);
                                --remaining1;
                            }
                        }
                        if (remaining1 <= 0) {
                            address5 = new TreeNode(input.substring(index5, offset), index5, elements2);
                            offset = offset;
                        } else {
                            address5 = FAILURE;
                        }
                        if (address5 != FAILURE) {
                            elements0.add(3, address5);
                            TreeNode address7 = FAILURE;
                            String chunk1 = null;
                            if (offset < inputSize) {
                                chunk1 = input.substring(offset, Math.min(offset + 1, input.length()));
                            }
                            if (chunk1 != null && chunk1.equals("=")) {
                                address7 = new TreeNode(input.substring(offset, offset + 1), offset);
                                offset = offset + 1;
                            } else {
                                address7 = FAILURE;
                                if (offset > failure) {
                                    failure = offset;
                                    expected = new ArrayList<String>();
                                }
                                if (offset == failure) {
                                    expected.add("\"=\"");
                                }
                            }
                            if (address7 != FAILURE) {
                                elements0.add(4, address7);
                                TreeNode address8 = FAILURE;
                                int remaining2 = 0;
                                int index6 = offset;
                                List<TreeNode> elements3 = new ArrayList<TreeNode>();
                                TreeNode address9 = new TreeNode("", -1);
                                while (address9 != FAILURE) {
                                    address9 = _read_ws();
                                    if (address9 != FAILURE) {
                                        elements3.add(address9);
                                        --remaining2;
                                    }
                                }
                                if (remaining2 <= 0) {
                                    address8 = new TreeNode(input.substring(index6, offset), index6, elements3);
                                    offset = offset;
                                } else {
                                    address8 = FAILURE;
                                }
                                if (address8 != FAILURE) {
                                    elements0.add(5, address8);
                                    TreeNode address10 = FAILURE;
                                    address10 = _read_decimal();
                                    if (address10 != FAILURE) {
                                        elements0.add(6, address10);
                                    } else {
                                        elements0 = null;
                                        offset = index2;
                                    }
                                } else {
                                    elements0 = null;
                                    offset = index2;
                                }
                            } else {
                                elements0 = null;
                                offset = index2;
                            }
                        } else {
                            elements0 = null;
                            offset = index2;
                        }
                    } else {
                        elements0 = null;
                        offset = index2;
                    }
                } else {
                    elements0 = null;
                    offset = index2;
                }
            } else {
                elements0 = null;
                offset = index2;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = actions.make_numeric_token(input, index2, offset, elements0);
                offset = offset;
            }
            if (address0 == FAILURE) {
                offset = index1;
                int index7 = offset;
                List<TreeNode> elements4 = new ArrayList<TreeNode>(7);
                TreeNode address11 = FAILURE;
                int index8 = offset;
                String chunk2 = null;
                if (offset < inputSize) {
                    chunk2 = input.substring(offset, Math.min(offset + 3, input.length()));
                }
                if (chunk2 != null && chunk2.equals("NOT")) {
                    address11 = new TreeNode(input.substring(offset, offset + 3), offset);
                    offset = offset + 3;
                } else {
                    address11 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String>();
                    }
                    if (offset == failure) {
                        expected.add("\"NOT\"");
                    }
                }
                if (address11 == FAILURE) {
                    address11 = new TreeNode(input.substring(index8, index8), index8);
                    offset = index8;
                }
                if (address11 != FAILURE) {
                    elements4.add(0, address11);
                    TreeNode address12 = FAILURE;
                    int remaining3 = 0;
                    int index9 = offset;
                    List<TreeNode> elements5 = new ArrayList<TreeNode>();
                    TreeNode address13 = new TreeNode("", -1);
                    while (address13 != FAILURE) {
                        address13 = _read_ws();
                        if (address13 != FAILURE) {
                            elements5.add(address13);
                            --remaining3;
                        }
                    }
                    if (remaining3 <= 0) {
                        address12 = new TreeNode(input.substring(index9, offset), index9, elements5);
                        offset = offset;
                    } else {
                        address12 = FAILURE;
                    }
                    if (address12 != FAILURE) {
                        elements4.add(1, address12);
                        TreeNode address14 = FAILURE;
                        address14 = _read_alphanumeric();
                        if (address14 != FAILURE) {
                            elements4.add(2, address14);
                            TreeNode address15 = FAILURE;
                            int remaining4 = 0;
                            int index10 = offset;
                            List<TreeNode> elements6 = new ArrayList<TreeNode>();
                            TreeNode address16 = new TreeNode("", -1);
                            while (address16 != FAILURE) {
                                address16 = _read_ws();
                                if (address16 != FAILURE) {
                                    elements6.add(address16);
                                    --remaining4;
                                }
                            }
                            if (remaining4 <= 0) {
                                address15 = new TreeNode(input.substring(index10, offset), index10, elements6);
                                offset = offset;
                            } else {
                                address15 = FAILURE;
                            }
                            if (address15 != FAILURE) {
                                elements4.add(3, address15);
                                TreeNode address17 = FAILURE;
                                String chunk3 = null;
                                if (offset < inputSize) {
                                    chunk3 = input.substring(offset, Math.min(offset + 1, input.length()));
                                }
                                if (chunk3 != null && chunk3.equals(">")) {
                                    address17 = new TreeNode(input.substring(offset, offset + 1), offset);
                                    offset = offset + 1;
                                } else {
                                    address17 = FAILURE;
                                    if (offset > failure) {
                                        failure = offset;
                                        expected = new ArrayList<String>();
                                    }
                                    if (offset == failure) {
                                        expected.add("\">\"");
                                    }
                                }
                                if (address17 != FAILURE) {
                                    elements4.add(4, address17);
                                    TreeNode address18 = FAILURE;
                                    int remaining5 = 0;
                                    int index11 = offset;
                                    List<TreeNode> elements7 = new ArrayList<TreeNode>();
                                    TreeNode address19 = new TreeNode("", -1);
                                    while (address19 != FAILURE) {
                                        address19 = _read_ws();
                                        if (address19 != FAILURE) {
                                            elements7.add(address19);
                                            --remaining5;
                                        }
                                    }
                                    if (remaining5 <= 0) {
                                        address18 = new TreeNode(input.substring(index11, offset), index11, elements7);
                                        offset = offset;
                                    } else {
                                        address18 = FAILURE;
                                    }
                                    if (address18 != FAILURE) {
                                        elements4.add(5, address18);
                                        TreeNode address20 = FAILURE;
                                        address20 = _read_app_version();
                                        if (address20 != FAILURE) {
                                            elements4.add(6, address20);
                                        } else {
                                            elements4 = null;
                                            offset = index7;
                                        }
                                    } else {
                                        elements4 = null;
                                        offset = index7;
                                    }
                                } else {
                                    elements4 = null;
                                    offset = index7;
                                }
                            } else {
                                elements4 = null;
                                offset = index7;
                            }
                        } else {
                            elements4 = null;
                            offset = index7;
                        }
                    } else {
                        elements4 = null;
                        offset = index7;
                    }
                } else {
                    elements4 = null;
                    offset = index7;
                }
                if (elements4 == null) {
                    address0 = FAILURE;
                } else {
                    address0 = actions.make_app_version_token(input, index7, offset, elements4);
                    offset = offset;
                }
                if (address0 == FAILURE) {
                    offset = index1;
                    int index12 = offset;
                    List<TreeNode> elements8 = new ArrayList<TreeNode>(7);
                    TreeNode address21 = FAILURE;
                    int index13 = offset;
                    String chunk4 = null;
                    if (offset < inputSize) {
                        chunk4 = input.substring(offset, Math.min(offset + 3, input.length()));
                    }
                    if (chunk4 != null && chunk4.equals("NOT")) {
                        address21 = new TreeNode(input.substring(offset, offset + 3), offset);
                        offset = offset + 3;
                    } else {
                        address21 = FAILURE;
                        if (offset > failure) {
                            failure = offset;
                            expected = new ArrayList<String>();
                        }
                        if (offset == failure) {
                            expected.add("\"NOT\"");
                        }
                    }
                    if (address21 == FAILURE) {
                        address21 = new TreeNode(input.substring(index13, index13), index13);
                        offset = index13;
                    }
                    if (address21 != FAILURE) {
                        elements8.add(0, address21);
                        TreeNode address22 = FAILURE;
                        int remaining6 = 0;
                        int index14 = offset;
                        List<TreeNode> elements9 = new ArrayList<TreeNode>();
                        TreeNode address23 = new TreeNode("", -1);
                        while (address23 != FAILURE) {
                            address23 = _read_ws();
                            if (address23 != FAILURE) {
                                elements9.add(address23);
                                --remaining6;
                            }
                        }
                        if (remaining6 <= 0) {
                            address22 = new TreeNode(input.substring(index14, offset), index14, elements9);
                            offset = offset;
                        } else {
                            address22 = FAILURE;
                        }
                        if (address22 != FAILURE) {
                            elements8.add(1, address22);
                            TreeNode address24 = FAILURE;
                            address24 = _read_alphanumeric();
                            if (address24 != FAILURE) {
                                elements8.add(2, address24);
                                TreeNode address25 = FAILURE;
                                int remaining7 = 0;
                                int index15 = offset;
                                List<TreeNode> elements10 = new ArrayList<TreeNode>();
                                TreeNode address26 = new TreeNode("", -1);
                                while (address26 != FAILURE) {
                                    address26 = _read_ws();
                                    if (address26 != FAILURE) {
                                        elements10.add(address26);
                                        --remaining7;
                                    }
                                }
                                if (remaining7 <= 0) {
                                    address25 = new TreeNode(input.substring(index15, offset), index15, elements10);
                                    offset = offset;
                                } else {
                                    address25 = FAILURE;
                                }
                                if (address25 != FAILURE) {
                                    elements8.add(3, address25);
                                    TreeNode address27 = FAILURE;
                                    String chunk5 = null;
                                    if (offset < inputSize) {
                                        chunk5 = input.substring(offset, Math.min(offset + 2, input.length()));
                                    }
                                    if (chunk5 != null && chunk5.equals(">=")) {
                                        address27 = new TreeNode(input.substring(offset, offset + 2), offset);
                                        offset = offset + 2;
                                    } else {
                                        address27 = FAILURE;
                                        if (offset > failure) {
                                            failure = offset;
                                            expected = new ArrayList<String>();
                                        }
                                        if (offset == failure) {
                                            expected.add("\">=\"");
                                        }
                                    }
                                    if (address27 != FAILURE) {
                                        elements8.add(4, address27);
                                        TreeNode address28 = FAILURE;
                                        int remaining8 = 0;
                                        int index16 = offset;
                                        List<TreeNode> elements11 = new ArrayList<TreeNode>();
                                        TreeNode address29 = new TreeNode("", -1);
                                        while (address29 != FAILURE) {
                                            address29 = _read_ws();
                                            if (address29 != FAILURE) {
                                                elements11.add(address29);
                                                --remaining8;
                                            }
                                        }
                                        if (remaining8 <= 0) {
                                            address28 = new TreeNode(input.substring(index16, offset), index16, elements11);
                                            offset = offset;
                                        } else {
                                            address28 = FAILURE;
                                        }
                                        if (address28 != FAILURE) {
                                            elements8.add(5, address28);
                                            TreeNode address30 = FAILURE;
                                            address30 = _read_app_version();
                                            if (address30 != FAILURE) {
                                                elements8.add(6, address30);
                                            } else {
                                                elements8 = null;
                                                offset = index12;
                                            }
                                        } else {
                                            elements8 = null;
                                            offset = index12;
                                        }
                                    } else {
                                        elements8 = null;
                                        offset = index12;
                                    }
                                } else {
                                    elements8 = null;
                                    offset = index12;
                                }
                            } else {
                                elements8 = null;
                                offset = index12;
                            }
                        } else {
                            elements8 = null;
                            offset = index12;
                        }
                    } else {
                        elements8 = null;
                        offset = index12;
                    }
                    if (elements8 == null) {
                        address0 = FAILURE;
                    } else {
                        address0 = actions.make_app_version_token(input, index12, offset, elements8);
                        offset = offset;
                    }
                    if (address0 == FAILURE) {
                        offset = index1;
                        int index17 = offset;
                        List<TreeNode> elements12 = new ArrayList<TreeNode>(7);
                        TreeNode address31 = FAILURE;
                        int index18 = offset;
                        String chunk6 = null;
                        if (offset < inputSize) {
                            chunk6 = input.substring(offset, Math.min(offset + 3, input.length()));
                        }
                        if (chunk6 != null && chunk6.equals("NOT")) {
                            address31 = new TreeNode(input.substring(offset, offset + 3), offset);
                            offset = offset + 3;
                        } else {
                            address31 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String>();
                            }
                            if (offset == failure) {
                                expected.add("\"NOT\"");
                            }
                        }
                        if (address31 == FAILURE) {
                            address31 = new TreeNode(input.substring(index18, index18), index18);
                            offset = index18;
                        }
                        if (address31 != FAILURE) {
                            elements12.add(0, address31);
                            TreeNode address32 = FAILURE;
                            int remaining9 = 0;
                            int index19 = offset;
                            List<TreeNode> elements13 = new ArrayList<TreeNode>();
                            TreeNode address33 = new TreeNode("", -1);
                            while (address33 != FAILURE) {
                                address33 = _read_ws();
                                if (address33 != FAILURE) {
                                    elements13.add(address33);
                                    --remaining9;
                                }
                            }
                            if (remaining9 <= 0) {
                                address32 = new TreeNode(input.substring(index19, offset), index19, elements13);
                                offset = offset;
                            } else {
                                address32 = FAILURE;
                            }
                            if (address32 != FAILURE) {
                                elements12.add(1, address32);
                                TreeNode address34 = FAILURE;
                                address34 = _read_alphanumeric();
                                if (address34 != FAILURE) {
                                    elements12.add(2, address34);
                                    TreeNode address35 = FAILURE;
                                    int remaining10 = 0;
                                    int index20 = offset;
                                    List<TreeNode> elements14 = new ArrayList<TreeNode>();
                                    TreeNode address36 = new TreeNode("", -1);
                                    while (address36 != FAILURE) {
                                        address36 = _read_ws();
                                        if (address36 != FAILURE) {
                                            elements14.add(address36);
                                            --remaining10;
                                        }
                                    }
                                    if (remaining10 <= 0) {
                                        address35 = new TreeNode(input.substring(index20, offset), index20, elements14);
                                        offset = offset;
                                    } else {
                                        address35 = FAILURE;
                                    }
                                    if (address35 != FAILURE) {
                                        elements12.add(3, address35);
                                        TreeNode address37 = FAILURE;
                                        String chunk7 = null;
                                        if (offset < inputSize) {
                                            chunk7 = input.substring(offset, Math.min(offset + 1, input.length()));
                                        }
                                        if (chunk7 != null && chunk7.equals("<")) {
                                            address37 = new TreeNode(input.substring(offset, offset + 1), offset);
                                            offset = offset + 1;
                                        } else {
                                            address37 = FAILURE;
                                            if (offset > failure) {
                                                failure = offset;
                                                expected = new ArrayList<String>();
                                            }
                                            if (offset == failure) {
                                                expected.add("\"<\"");
                                            }
                                        }
                                        if (address37 != FAILURE) {
                                            elements12.add(4, address37);
                                            TreeNode address38 = FAILURE;
                                            int remaining11 = 0;
                                            int index21 = offset;
                                            List<TreeNode> elements15 = new ArrayList<TreeNode>();
                                            TreeNode address39 = new TreeNode("", -1);
                                            while (address39 != FAILURE) {
                                                address39 = _read_ws();
                                                if (address39 != FAILURE) {
                                                    elements15.add(address39);
                                                    --remaining11;
                                                }
                                            }
                                            if (remaining11 <= 0) {
                                                address38 = new TreeNode(input.substring(index21, offset), index21, elements15);
                                                offset = offset;
                                            } else {
                                                address38 = FAILURE;
                                            }
                                            if (address38 != FAILURE) {
                                                elements12.add(5, address38);
                                                TreeNode address40 = FAILURE;
                                                address40 = _read_app_version();
                                                if (address40 != FAILURE) {
                                                    elements12.add(6, address40);
                                                } else {
                                                    elements12 = null;
                                                    offset = index17;
                                                }
                                            } else {
                                                elements12 = null;
                                                offset = index17;
                                            }
                                        } else {
                                            elements12 = null;
                                            offset = index17;
                                        }
                                    } else {
                                        elements12 = null;
                                        offset = index17;
                                    }
                                } else {
                                    elements12 = null;
                                    offset = index17;
                                }
                            } else {
                                elements12 = null;
                                offset = index17;
                            }
                        } else {
                            elements12 = null;
                            offset = index17;
                        }
                        if (elements12 == null) {
                            address0 = FAILURE;
                        } else {
                            address0 = actions.make_app_version_token(input, index17, offset, elements12);
                            offset = offset;
                        }
                        if (address0 == FAILURE) {
                            offset = index1;
                            int index22 = offset;
                            List<TreeNode> elements16 = new ArrayList<TreeNode>(7);
                            TreeNode address41 = FAILURE;
                            int index23 = offset;
                            String chunk8 = null;
                            if (offset < inputSize) {
                                chunk8 = input.substring(offset, Math.min(offset + 3, input.length()));
                            }
                            if (chunk8 != null && chunk8.equals("NOT")) {
                                address41 = new TreeNode(input.substring(offset, offset + 3), offset);
                                offset = offset + 3;
                            } else {
                                address41 = FAILURE;
                                if (offset > failure) {
                                    failure = offset;
                                    expected = new ArrayList<String>();
                                }
                                if (offset == failure) {
                                    expected.add("\"NOT\"");
                                }
                            }
                            if (address41 == FAILURE) {
                                address41 = new TreeNode(input.substring(index23, index23), index23);
                                offset = index23;
                            }
                            if (address41 != FAILURE) {
                                elements16.add(0, address41);
                                TreeNode address42 = FAILURE;
                                int remaining12 = 0;
                                int index24 = offset;
                                List<TreeNode> elements17 = new ArrayList<TreeNode>();
                                TreeNode address43 = new TreeNode("", -1);
                                while (address43 != FAILURE) {
                                    address43 = _read_ws();
                                    if (address43 != FAILURE) {
                                        elements17.add(address43);
                                        --remaining12;
                                    }
                                }
                                if (remaining12 <= 0) {
                                    address42 = new TreeNode(input.substring(index24, offset), index24, elements17);
                                    offset = offset;
                                } else {
                                    address42 = FAILURE;
                                }
                                if (address42 != FAILURE) {
                                    elements16.add(1, address42);
                                    TreeNode address44 = FAILURE;
                                    address44 = _read_alphanumeric();
                                    if (address44 != FAILURE) {
                                        elements16.add(2, address44);
                                        TreeNode address45 = FAILURE;
                                        int remaining13 = 0;
                                        int index25 = offset;
                                        List<TreeNode> elements18 = new ArrayList<TreeNode>();
                                        TreeNode address46 = new TreeNode("", -1);
                                        while (address46 != FAILURE) {
                                            address46 = _read_ws();
                                            if (address46 != FAILURE) {
                                                elements18.add(address46);
                                                --remaining13;
                                            }
                                        }
                                        if (remaining13 <= 0) {
                                            address45 = new TreeNode(input.substring(index25, offset), index25, elements18);
                                            offset = offset;
                                        } else {
                                            address45 = FAILURE;
                                        }
                                        if (address45 != FAILURE) {
                                            elements16.add(3, address45);
                                            TreeNode address47 = FAILURE;
                                            String chunk9 = null;
                                            if (offset < inputSize) {
                                                chunk9 = input.substring(offset, Math.min(offset + 2, input.length()));
                                            }
                                            if (chunk9 != null && chunk9.equals("<=")) {
                                                address47 = new TreeNode(input.substring(offset, offset + 2), offset);
                                                offset = offset + 2;
                                            } else {
                                                address47 = FAILURE;
                                                if (offset > failure) {
                                                    failure = offset;
                                                    expected = new ArrayList<String>();
                                                }
                                                if (offset == failure) {
                                                    expected.add("\"<=\"");
                                                }
                                            }
                                            if (address47 != FAILURE) {
                                                elements16.add(4, address47);
                                                TreeNode address48 = FAILURE;
                                                int remaining14 = 0;
                                                int index26 = offset;
                                                List<TreeNode> elements19 = new ArrayList<TreeNode>();
                                                TreeNode address49 = new TreeNode("", -1);
                                                while (address49 != FAILURE) {
                                                    address49 = _read_ws();
                                                    if (address49 != FAILURE) {
                                                        elements19.add(address49);
                                                        --remaining14;
                                                    }
                                                }
                                                if (remaining14 <= 0) {
                                                    address48 = new TreeNode(input.substring(index26, offset), index26, elements19);
                                                    offset = offset;
                                                } else {
                                                    address48 = FAILURE;
                                                }
                                                if (address48 != FAILURE) {
                                                    elements16.add(5, address48);
                                                    TreeNode address50 = FAILURE;
                                                    address50 = _read_app_version();
                                                    if (address50 != FAILURE) {
                                                        elements16.add(6, address50);
                                                    } else {
                                                        elements16 = null;
                                                        offset = index22;
                                                    }
                                                } else {
                                                    elements16 = null;
                                                    offset = index22;
                                                }
                                            } else {
                                                elements16 = null;
                                                offset = index22;
                                            }
                                        } else {
                                            elements16 = null;
                                            offset = index22;
                                        }
                                    } else {
                                        elements16 = null;
                                        offset = index22;
                                    }
                                } else {
                                    elements16 = null;
                                    offset = index22;
                                }
                            } else {
                                elements16 = null;
                                offset = index22;
                            }
                            if (elements16 == null) {
                                address0 = FAILURE;
                            } else {
                                address0 = actions.make_app_version_token(input, index22, offset, elements16);
                                offset = offset;
                            }
                            if (address0 == FAILURE) {
                                offset = index1;
                                int index27 = offset;
                                List<TreeNode> elements20 = new ArrayList<TreeNode>(7);
                                TreeNode address51 = FAILURE;
                                int index28 = offset;
                                String chunk10 = null;
                                if (offset < inputSize) {
                                    chunk10 = input.substring(offset, Math.min(offset + 3, input.length()));
                                }
                                if (chunk10 != null && chunk10.equals("NOT")) {
                                    address51 = new TreeNode(input.substring(offset, offset + 3), offset);
                                    offset = offset + 3;
                                } else {
                                    address51 = FAILURE;
                                    if (offset > failure) {
                                        failure = offset;
                                        expected = new ArrayList<String>();
                                    }
                                    if (offset == failure) {
                                        expected.add("\"NOT\"");
                                    }
                                }
                                if (address51 == FAILURE) {
                                    address51 = new TreeNode(input.substring(index28, index28), index28);
                                    offset = index28;
                                }
                                if (address51 != FAILURE) {
                                    elements20.add(0, address51);
                                    TreeNode address52 = FAILURE;
                                    int remaining15 = 0;
                                    int index29 = offset;
                                    List<TreeNode> elements21 = new ArrayList<TreeNode>();
                                    TreeNode address53 = new TreeNode("", -1);
                                    while (address53 != FAILURE) {
                                        address53 = _read_ws();
                                        if (address53 != FAILURE) {
                                            elements21.add(address53);
                                            --remaining15;
                                        }
                                    }
                                    if (remaining15 <= 0) {
                                        address52 = new TreeNode(input.substring(index29, offset), index29, elements21);
                                        offset = offset;
                                    } else {
                                        address52 = FAILURE;
                                    }
                                    if (address52 != FAILURE) {
                                        elements20.add(1, address52);
                                        TreeNode address54 = FAILURE;
                                        address54 = _read_alphanumeric();
                                        if (address54 != FAILURE) {
                                            elements20.add(2, address54);
                                            TreeNode address55 = FAILURE;
                                            int remaining16 = 0;
                                            int index30 = offset;
                                            List<TreeNode> elements22 = new ArrayList<TreeNode>();
                                            TreeNode address56 = new TreeNode("", -1);
                                            while (address56 != FAILURE) {
                                                address56 = _read_ws();
                                                if (address56 != FAILURE) {
                                                    elements22.add(address56);
                                                    --remaining16;
                                                }
                                            }
                                            if (remaining16 <= 0) {
                                                address55 = new TreeNode(input.substring(index30, offset), index30, elements22);
                                                offset = offset;
                                            } else {
                                                address55 = FAILURE;
                                            }
                                            if (address55 != FAILURE) {
                                                elements20.add(3, address55);
                                                TreeNode address57 = FAILURE;
                                                String chunk11 = null;
                                                if (offset < inputSize) {
                                                    chunk11 = input.substring(offset, Math.min(offset + 1, input.length()));
                                                }
                                                if (chunk11 != null && chunk11.equals(">")) {
                                                    address57 = new TreeNode(input.substring(offset, offset + 1), offset);
                                                    offset = offset + 1;
                                                } else {
                                                    address57 = FAILURE;
                                                    if (offset > failure) {
                                                        failure = offset;
                                                        expected = new ArrayList<String>();
                                                    }
                                                    if (offset == failure) {
                                                        expected.add("\">\"");
                                                    }
                                                }
                                                if (address57 != FAILURE) {
                                                    elements20.add(4, address57);
                                                    TreeNode address58 = FAILURE;
                                                    int remaining17 = 0;
                                                    int index31 = offset;
                                                    List<TreeNode> elements23 = new ArrayList<TreeNode>();
                                                    TreeNode address59 = new TreeNode("", -1);
                                                    while (address59 != FAILURE) {
                                                        address59 = _read_ws();
                                                        if (address59 != FAILURE) {
                                                            elements23.add(address59);
                                                            --remaining17;
                                                        }
                                                    }
                                                    if (remaining17 <= 0) {
                                                        address58 = new TreeNode(input.substring(index31, offset), index31, elements23);
                                                        offset = offset;
                                                    } else {
                                                        address58 = FAILURE;
                                                    }
                                                    if (address58 != FAILURE) {
                                                        elements20.add(5, address58);
                                                        TreeNode address60 = FAILURE;
                                                        address60 = _read_decimal();
                                                        if (address60 != FAILURE) {
                                                            elements20.add(6, address60);
                                                        } else {
                                                            elements20 = null;
                                                            offset = index27;
                                                        }
                                                    } else {
                                                        elements20 = null;
                                                        offset = index27;
                                                    }
                                                } else {
                                                    elements20 = null;
                                                    offset = index27;
                                                }
                                            } else {
                                                elements20 = null;
                                                offset = index27;
                                            }
                                        } else {
                                            elements20 = null;
                                            offset = index27;
                                        }
                                    } else {
                                        elements20 = null;
                                        offset = index27;
                                    }
                                } else {
                                    elements20 = null;
                                    offset = index27;
                                }
                                if (elements20 == null) {
                                    address0 = FAILURE;
                                } else {
                                    address0 = actions.make_numeric_token(input, index27, offset, elements20);
                                    offset = offset;
                                }
                                if (address0 == FAILURE) {
                                    offset = index1;
                                    int index32 = offset;
                                    List<TreeNode> elements24 = new ArrayList<TreeNode>(7);
                                    TreeNode address61 = FAILURE;
                                    int index33 = offset;
                                    String chunk12 = null;
                                    if (offset < inputSize) {
                                        chunk12 = input.substring(offset, Math.min(offset + 3, input.length()));
                                    }
                                    if (chunk12 != null && chunk12.equals("NOT")) {
                                        address61 = new TreeNode(input.substring(offset, offset + 3), offset);
                                        offset = offset + 3;
                                    } else {
                                        address61 = FAILURE;
                                        if (offset > failure) {
                                            failure = offset;
                                            expected = new ArrayList<String>();
                                        }
                                        if (offset == failure) {
                                            expected.add("\"NOT\"");
                                        }
                                    }
                                    if (address61 == FAILURE) {
                                        address61 = new TreeNode(input.substring(index33, index33), index33);
                                        offset = index33;
                                    }
                                    if (address61 != FAILURE) {
                                        elements24.add(0, address61);
                                        TreeNode address62 = FAILURE;
                                        int remaining18 = 0;
                                        int index34 = offset;
                                        List<TreeNode> elements25 = new ArrayList<TreeNode>();
                                        TreeNode address63 = new TreeNode("", -1);
                                        while (address63 != FAILURE) {
                                            address63 = _read_ws();
                                            if (address63 != FAILURE) {
                                                elements25.add(address63);
                                                --remaining18;
                                            }
                                        }
                                        if (remaining18 <= 0) {
                                            address62 = new TreeNode(input.substring(index34, offset), index34, elements25);
                                            offset = offset;
                                        } else {
                                            address62 = FAILURE;
                                        }
                                        if (address62 != FAILURE) {
                                            elements24.add(1, address62);
                                            TreeNode address64 = FAILURE;
                                            address64 = _read_alphanumeric();
                                            if (address64 != FAILURE) {
                                                elements24.add(2, address64);
                                                TreeNode address65 = FAILURE;
                                                int remaining19 = 0;
                                                int index35 = offset;
                                                List<TreeNode> elements26 = new ArrayList<TreeNode>();
                                                TreeNode address66 = new TreeNode("", -1);
                                                while (address66 != FAILURE) {
                                                    address66 = _read_ws();
                                                    if (address66 != FAILURE) {
                                                        elements26.add(address66);
                                                        --remaining19;
                                                    }
                                                }
                                                if (remaining19 <= 0) {
                                                    address65 = new TreeNode(input.substring(index35, offset), index35, elements26);
                                                    offset = offset;
                                                } else {
                                                    address65 = FAILURE;
                                                }
                                                if (address65 != FAILURE) {
                                                    elements24.add(3, address65);
                                                    TreeNode address67 = FAILURE;
                                                    String chunk13 = null;
                                                    if (offset < inputSize) {
                                                        chunk13 = input.substring(offset, Math.min(offset + 2, input.length()));
                                                    }
                                                    if (chunk13 != null && chunk13.equals(">=")) {
                                                        address67 = new TreeNode(input.substring(offset, offset + 2), offset);
                                                        offset = offset + 2;
                                                    } else {
                                                        address67 = FAILURE;
                                                        if (offset > failure) {
                                                            failure = offset;
                                                            expected = new ArrayList<String>();
                                                        }
                                                        if (offset == failure) {
                                                            expected.add("\">=\"");
                                                        }
                                                    }
                                                    if (address67 != FAILURE) {
                                                        elements24.add(4, address67);
                                                        TreeNode address68 = FAILURE;
                                                        int remaining20 = 0;
                                                        int index36 = offset;
                                                        List<TreeNode> elements27 = new ArrayList<TreeNode>();
                                                        TreeNode address69 = new TreeNode("", -1);
                                                        while (address69 != FAILURE) {
                                                            address69 = _read_ws();
                                                            if (address69 != FAILURE) {
                                                                elements27.add(address69);
                                                                --remaining20;
                                                            }
                                                        }
                                                        if (remaining20 <= 0) {
                                                            address68 = new TreeNode(input.substring(index36, offset), index36, elements27);
                                                            offset = offset;
                                                        } else {
                                                            address68 = FAILURE;
                                                        }
                                                        if (address68 != FAILURE) {
                                                            elements24.add(5, address68);
                                                            TreeNode address70 = FAILURE;
                                                            address70 = _read_decimal();
                                                            if (address70 != FAILURE) {
                                                                elements24.add(6, address70);
                                                            } else {
                                                                elements24 = null;
                                                                offset = index32;
                                                            }
                                                        } else {
                                                            elements24 = null;
                                                            offset = index32;
                                                        }
                                                    } else {
                                                        elements24 = null;
                                                        offset = index32;
                                                    }
                                                } else {
                                                    elements24 = null;
                                                    offset = index32;
                                                }
                                            } else {
                                                elements24 = null;
                                                offset = index32;
                                            }
                                        } else {
                                            elements24 = null;
                                            offset = index32;
                                        }
                                    } else {
                                        elements24 = null;
                                        offset = index32;
                                    }
                                    if (elements24 == null) {
                                        address0 = FAILURE;
                                    } else {
                                        address0 = actions.make_numeric_token(input, index32, offset, elements24);
                                        offset = offset;
                                    }
                                    if (address0 == FAILURE) {
                                        offset = index1;
                                        int index37 = offset;
                                        List<TreeNode> elements28 = new ArrayList<TreeNode>(7);
                                        TreeNode address71 = FAILURE;
                                        int index38 = offset;
                                        String chunk14 = null;
                                        if (offset < inputSize) {
                                            chunk14 = input.substring(offset, Math.min(offset + 3, input.length()));
                                        }
                                        if (chunk14 != null && chunk14.equals("NOT")) {
                                            address71 = new TreeNode(input.substring(offset, offset + 3), offset);
                                            offset = offset + 3;
                                        } else {
                                            address71 = FAILURE;
                                            if (offset > failure) {
                                                failure = offset;
                                                expected = new ArrayList<String>();
                                            }
                                            if (offset == failure) {
                                                expected.add("\"NOT\"");
                                            }
                                        }
                                        if (address71 == FAILURE) {
                                            address71 = new TreeNode(input.substring(index38, index38), index38);
                                            offset = index38;
                                        }
                                        if (address71 != FAILURE) {
                                            elements28.add(0, address71);
                                            TreeNode address72 = FAILURE;
                                            int remaining21 = 0;
                                            int index39 = offset;
                                            List<TreeNode> elements29 = new ArrayList<TreeNode>();
                                            TreeNode address73 = new TreeNode("", -1);
                                            while (address73 != FAILURE) {
                                                address73 = _read_ws();
                                                if (address73 != FAILURE) {
                                                    elements29.add(address73);
                                                    --remaining21;
                                                }
                                            }
                                            if (remaining21 <= 0) {
                                                address72 = new TreeNode(input.substring(index39, offset), index39, elements29);
                                                offset = offset;
                                            } else {
                                                address72 = FAILURE;
                                            }
                                            if (address72 != FAILURE) {
                                                elements28.add(1, address72);
                                                TreeNode address74 = FAILURE;
                                                address74 = _read_alphanumeric();
                                                if (address74 != FAILURE) {
                                                    elements28.add(2, address74);
                                                    TreeNode address75 = FAILURE;
                                                    int remaining22 = 0;
                                                    int index40 = offset;
                                                    List<TreeNode> elements30 = new ArrayList<TreeNode>();
                                                    TreeNode address76 = new TreeNode("", -1);
                                                    while (address76 != FAILURE) {
                                                        address76 = _read_ws();
                                                        if (address76 != FAILURE) {
                                                            elements30.add(address76);
                                                            --remaining22;
                                                        }
                                                    }
                                                    if (remaining22 <= 0) {
                                                        address75 = new TreeNode(input.substring(index40, offset), index40, elements30);
                                                        offset = offset;
                                                    } else {
                                                        address75 = FAILURE;
                                                    }
                                                    if (address75 != FAILURE) {
                                                        elements28.add(3, address75);
                                                        TreeNode address77 = FAILURE;
                                                        String chunk15 = null;
                                                        if (offset < inputSize) {
                                                            chunk15 = input.substring(offset, Math.min(offset + 1, input.length()));
                                                        }
                                                        if (chunk15 != null && chunk15.equals("<")) {
                                                            address77 = new TreeNode(input.substring(offset, offset + 1), offset);
                                                            offset = offset + 1;
                                                        } else {
                                                            address77 = FAILURE;
                                                            if (offset > failure) {
                                                                failure = offset;
                                                                expected = new ArrayList<String>();
                                                            }
                                                            if (offset == failure) {
                                                                expected.add("\"<\"");
                                                            }
                                                        }
                                                        if (address77 != FAILURE) {
                                                            elements28.add(4, address77);
                                                            TreeNode address78 = FAILURE;
                                                            int remaining23 = 0;
                                                            int index41 = offset;
                                                            List<TreeNode> elements31 = new ArrayList<TreeNode>();
                                                            TreeNode address79 = new TreeNode("", -1);
                                                            while (address79 != FAILURE) {
                                                                address79 = _read_ws();
                                                                if (address79 != FAILURE) {
                                                                    elements31.add(address79);
                                                                    --remaining23;
                                                                }
                                                            }
                                                            if (remaining23 <= 0) {
                                                                address78 = new TreeNode(input.substring(index41, offset), index41, elements31);
                                                                offset = offset;
                                                            } else {
                                                                address78 = FAILURE;
                                                            }
                                                            if (address78 != FAILURE) {
                                                                elements28.add(5, address78);
                                                                TreeNode address80 = FAILURE;
                                                                address80 = _read_decimal();
                                                                if (address80 != FAILURE) {
                                                                    elements28.add(6, address80);
                                                                } else {
                                                                    elements28 = null;
                                                                    offset = index37;
                                                                }
                                                            } else {
                                                                elements28 = null;
                                                                offset = index37;
                                                            }
                                                        } else {
                                                            elements28 = null;
                                                            offset = index37;
                                                        }
                                                    } else {
                                                        elements28 = null;
                                                        offset = index37;
                                                    }
                                                } else {
                                                    elements28 = null;
                                                    offset = index37;
                                                }
                                            } else {
                                                elements28 = null;
                                                offset = index37;
                                            }
                                        } else {
                                            elements28 = null;
                                            offset = index37;
                                        }
                                        if (elements28 == null) {
                                            address0 = FAILURE;
                                        } else {
                                            address0 = actions.make_numeric_token(input, index37, offset, elements28);
                                            offset = offset;
                                        }
                                        if (address0 == FAILURE) {
                                            offset = index1;
                                            int index42 = offset;
                                            List<TreeNode> elements32 = new ArrayList<TreeNode>(7);
                                            TreeNode address81 = FAILURE;
                                            int index43 = offset;
                                            String chunk16 = null;
                                            if (offset < inputSize) {
                                                chunk16 = input.substring(offset, Math.min(offset + 3, input.length()));
                                            }
                                            if (chunk16 != null && chunk16.equals("NOT")) {
                                                address81 = new TreeNode(input.substring(offset, offset + 3), offset);
                                                offset = offset + 3;
                                            } else {
                                                address81 = FAILURE;
                                                if (offset > failure) {
                                                    failure = offset;
                                                    expected = new ArrayList<String>();
                                                }
                                                if (offset == failure) {
                                                    expected.add("\"NOT\"");
                                                }
                                            }
                                            if (address81 == FAILURE) {
                                                address81 = new TreeNode(input.substring(index43, index43), index43);
                                                offset = index43;
                                            }
                                            if (address81 != FAILURE) {
                                                elements32.add(0, address81);
                                                TreeNode address82 = FAILURE;
                                                int remaining24 = 0;
                                                int index44 = offset;
                                                List<TreeNode> elements33 = new ArrayList<TreeNode>();
                                                TreeNode address83 = new TreeNode("", -1);
                                                while (address83 != FAILURE) {
                                                    address83 = _read_ws();
                                                    if (address83 != FAILURE) {
                                                        elements33.add(address83);
                                                        --remaining24;
                                                    }
                                                }
                                                if (remaining24 <= 0) {
                                                    address82 = new TreeNode(input.substring(index44, offset), index44, elements33);
                                                    offset = offset;
                                                } else {
                                                    address82 = FAILURE;
                                                }
                                                if (address82 != FAILURE) {
                                                    elements32.add(1, address82);
                                                    TreeNode address84 = FAILURE;
                                                    address84 = _read_alphanumeric();
                                                    if (address84 != FAILURE) {
                                                        elements32.add(2, address84);
                                                        TreeNode address85 = FAILURE;
                                                        int remaining25 = 0;
                                                        int index45 = offset;
                                                        List<TreeNode> elements34 = new ArrayList<TreeNode>();
                                                        TreeNode address86 = new TreeNode("", -1);
                                                        while (address86 != FAILURE) {
                                                            address86 = _read_ws();
                                                            if (address86 != FAILURE) {
                                                                elements34.add(address86);
                                                                --remaining25;
                                                            }
                                                        }
                                                        if (remaining25 <= 0) {
                                                            address85 = new TreeNode(input.substring(index45, offset), index45, elements34);
                                                            offset = offset;
                                                        } else {
                                                            address85 = FAILURE;
                                                        }
                                                        if (address85 != FAILURE) {
                                                            elements32.add(3, address85);
                                                            TreeNode address87 = FAILURE;
                                                            String chunk17 = null;
                                                            if (offset < inputSize) {
                                                                chunk17 = input.substring(offset, Math.min(offset + 2, input.length()));
                                                            }
                                                            if (chunk17 != null && chunk17.equals("<=")) {
                                                                address87 = new TreeNode(input.substring(offset, offset + 2), offset);
                                                                offset = offset + 2;
                                                            } else {
                                                                address87 = FAILURE;
                                                                if (offset > failure) {
                                                                    failure = offset;
                                                                    expected = new ArrayList<String>();
                                                                }
                                                                if (offset == failure) {
                                                                    expected.add("\"<=\"");
                                                                }
                                                            }
                                                            if (address87 != FAILURE) {
                                                                elements32.add(4, address87);
                                                                TreeNode address88 = FAILURE;
                                                                int remaining26 = 0;
                                                                int index46 = offset;
                                                                List<TreeNode> elements35 = new ArrayList<TreeNode>();
                                                                TreeNode address89 = new TreeNode("", -1);
                                                                while (address89 != FAILURE) {
                                                                    address89 = _read_ws();
                                                                    if (address89 != FAILURE) {
                                                                        elements35.add(address89);
                                                                        --remaining26;
                                                                    }
                                                                }
                                                                if (remaining26 <= 0) {
                                                                    address88 = new TreeNode(input.substring(index46, offset), index46, elements35);
                                                                    offset = offset;
                                                                } else {
                                                                    address88 = FAILURE;
                                                                }
                                                                if (address88 != FAILURE) {
                                                                    elements32.add(5, address88);
                                                                    TreeNode address90 = FAILURE;
                                                                    address90 = _read_decimal();
                                                                    if (address90 != FAILURE) {
                                                                        elements32.add(6, address90);
                                                                    } else {
                                                                        elements32 = null;
                                                                        offset = index42;
                                                                    }
                                                                } else {
                                                                    elements32 = null;
                                                                    offset = index42;
                                                                }
                                                            } else {
                                                                elements32 = null;
                                                                offset = index42;
                                                            }
                                                        } else {
                                                            elements32 = null;
                                                            offset = index42;
                                                        }
                                                    } else {
                                                        elements32 = null;
                                                        offset = index42;
                                                    }
                                                } else {
                                                    elements32 = null;
                                                    offset = index42;
                                                }
                                            } else {
                                                elements32 = null;
                                                offset = index42;
                                            }
                                            if (elements32 == null) {
                                                address0 = FAILURE;
                                            } else {
                                                address0 = actions.make_numeric_token(input, index42, offset, elements32);
                                                offset = offset;
                                            }
                                            if (address0 == FAILURE) {
                                                offset = index1;
                                                int index47 = offset;
                                                List<TreeNode> elements36 = new ArrayList<TreeNode>(7);
                                                TreeNode address91 = FAILURE;
                                                int index48 = offset;
                                                String chunk18 = null;
                                                if (offset < inputSize) {
                                                    chunk18 = input.substring(offset, Math.min(offset + 3, input.length()));
                                                }
                                                if (chunk18 != null && chunk18.equals("NOT")) {
                                                    address91 = new TreeNode(input.substring(offset, offset + 3), offset);
                                                    offset = offset + 3;
                                                } else {
                                                    address91 = FAILURE;
                                                    if (offset > failure) {
                                                        failure = offset;
                                                        expected = new ArrayList<String>();
                                                    }
                                                    if (offset == failure) {
                                                        expected.add("\"NOT\"");
                                                    }
                                                }
                                                if (address91 == FAILURE) {
                                                    address91 = new TreeNode(input.substring(index48, index48), index48);
                                                    offset = index48;
                                                }
                                                if (address91 != FAILURE) {
                                                    elements36.add(0, address91);
                                                    TreeNode address92 = FAILURE;
                                                    int remaining27 = 0;
                                                    int index49 = offset;
                                                    List<TreeNode> elements37 = new ArrayList<TreeNode>();
                                                    TreeNode address93 = new TreeNode("", -1);
                                                    while (address93 != FAILURE) {
                                                        address93 = _read_ws();
                                                        if (address93 != FAILURE) {
                                                            elements37.add(address93);
                                                            --remaining27;
                                                        }
                                                    }
                                                    if (remaining27 <= 0) {
                                                        address92 = new TreeNode(input.substring(index49, offset), index49, elements37);
                                                        offset = offset;
                                                    } else {
                                                        address92 = FAILURE;
                                                    }
                                                    if (address92 != FAILURE) {
                                                        elements36.add(1, address92);
                                                        TreeNode address94 = FAILURE;
                                                        address94 = _read_alphanumeric();
                                                        if (address94 != FAILURE) {
                                                            elements36.add(2, address94);
                                                            TreeNode address95 = FAILURE;
                                                            int remaining28 = 0;
                                                            int index50 = offset;
                                                            List<TreeNode> elements38 = new ArrayList<TreeNode>();
                                                            TreeNode address96 = new TreeNode("", -1);
                                                            while (address96 != FAILURE) {
                                                                address96 = _read_ws();
                                                                if (address96 != FAILURE) {
                                                                    elements38.add(address96);
                                                                    --remaining28;
                                                                }
                                                            }
                                                            if (remaining28 <= 0) {
                                                                address95 = new TreeNode(input.substring(index50, offset), index50, elements38);
                                                                offset = offset;
                                                            } else {
                                                                address95 = FAILURE;
                                                            }
                                                            if (address95 != FAILURE) {
                                                                elements36.add(3, address95);
                                                                TreeNode address97 = FAILURE;
                                                                String chunk19 = null;
                                                                if (offset < inputSize) {
                                                                    chunk19 = input.substring(offset, Math.min(offset + 2, input.length()));
                                                                }
                                                                if (chunk19 != null && chunk19.equals("!=")) {
                                                                    address97 = new TreeNode(input.substring(offset, offset + 2), offset);
                                                                    offset = offset + 2;
                                                                } else {
                                                                    address97 = FAILURE;
                                                                    if (offset > failure) {
                                                                        failure = offset;
                                                                        expected = new ArrayList<String>();
                                                                    }
                                                                    if (offset == failure) {
                                                                        expected.add("\"!=\"");
                                                                    }
                                                                }
                                                                if (address97 != FAILURE) {
                                                                    elements36.add(4, address97);
                                                                    TreeNode address98 = FAILURE;
                                                                    int remaining29 = 0;
                                                                    int index51 = offset;
                                                                    List<TreeNode> elements39 = new ArrayList<TreeNode>();
                                                                    TreeNode address99 = new TreeNode("", -1);
                                                                    while (address99 != FAILURE) {
                                                                        address99 = _read_ws();
                                                                        if (address99 != FAILURE) {
                                                                            elements39.add(address99);
                                                                            --remaining29;
                                                                        }
                                                                    }
                                                                    if (remaining29 <= 0) {
                                                                        address98 = new TreeNode(input.substring(index51, offset), index51, elements39);
                                                                        offset = offset;
                                                                    } else {
                                                                        address98 = FAILURE;
                                                                    }
                                                                    if (address98 != FAILURE) {
                                                                        elements36.add(5, address98);
                                                                        TreeNode address100 = FAILURE;
                                                                        address100 = _read_decimal();
                                                                        if (address100 != FAILURE) {
                                                                            elements36.add(6, address100);
                                                                        } else {
                                                                            elements36 = null;
                                                                            offset = index47;
                                                                        }
                                                                    } else {
                                                                        elements36 = null;
                                                                        offset = index47;
                                                                    }
                                                                } else {
                                                                    elements36 = null;
                                                                    offset = index47;
                                                                }
                                                            } else {
                                                                elements36 = null;
                                                                offset = index47;
                                                            }
                                                        } else {
                                                            elements36 = null;
                                                            offset = index47;
                                                        }
                                                    } else {
                                                        elements36 = null;
                                                        offset = index47;
                                                    }
                                                } else {
                                                    elements36 = null;
                                                    offset = index47;
                                                }
                                                if (elements36 == null) {
                                                    address0 = FAILURE;
                                                } else {
                                                    address0 = actions.make_numeric_token(input, index47, offset, elements36);
                                                    offset = offset;
                                                }
                                                if (address0 == FAILURE) {
                                                    offset = index1;
                                                    int index52 = offset;
                                                    List<TreeNode> elements40 = new ArrayList<TreeNode>(11);
                                                    TreeNode address101 = FAILURE;
                                                    int index53 = offset;
                                                    String chunk20 = null;
                                                    if (offset < inputSize) {
                                                        chunk20 = input.substring(offset, Math.min(offset + 3, input.length()));
                                                    }
                                                    if (chunk20 != null && chunk20.equals("NOT")) {
                                                        address101 = new TreeNode(input.substring(offset, offset + 3), offset);
                                                        offset = offset + 3;
                                                    } else {
                                                        address101 = FAILURE;
                                                        if (offset > failure) {
                                                            failure = offset;
                                                            expected = new ArrayList<String>();
                                                        }
                                                        if (offset == failure) {
                                                            expected.add("\"NOT\"");
                                                        }
                                                    }
                                                    if (address101 == FAILURE) {
                                                        address101 = new TreeNode(input.substring(index53, index53), index53);
                                                        offset = index53;
                                                    }
                                                    if (address101 != FAILURE) {
                                                        elements40.add(0, address101);
                                                        TreeNode address102 = FAILURE;
                                                        int remaining30 = 0;
                                                        int index54 = offset;
                                                        List<TreeNode> elements41 = new ArrayList<TreeNode>();
                                                        TreeNode address103 = new TreeNode("", -1);
                                                        while (address103 != FAILURE) {
                                                            address103 = _read_ws();
                                                            if (address103 != FAILURE) {
                                                                elements41.add(address103);
                                                                --remaining30;
                                                            }
                                                        }
                                                        if (remaining30 <= 0) {
                                                            address102 = new TreeNode(input.substring(index54, offset), index54, elements41);
                                                            offset = offset;
                                                        } else {
                                                            address102 = FAILURE;
                                                        }
                                                        if (address102 != FAILURE) {
                                                            elements40.add(1, address102);
                                                            TreeNode address104 = FAILURE;
                                                            address104 = _read_alphanumeric();
                                                            if (address104 != FAILURE) {
                                                                elements40.add(2, address104);
                                                                TreeNode address105 = FAILURE;
                                                                int remaining31 = 0;
                                                                int index55 = offset;
                                                                List<TreeNode> elements42 = new ArrayList<TreeNode>();
                                                                TreeNode address106 = new TreeNode("", -1);
                                                                while (address106 != FAILURE) {
                                                                    address106 = _read_ws();
                                                                    if (address106 != FAILURE) {
                                                                        elements42.add(address106);
                                                                        --remaining31;
                                                                    }
                                                                }
                                                                if (remaining31 <= 0) {
                                                                    address105 = new TreeNode(input.substring(index55, offset), index55, elements42);
                                                                    offset = offset;
                                                                } else {
                                                                    address105 = FAILURE;
                                                                }
                                                                if (address105 != FAILURE) {
                                                                    elements40.add(3, address105);
                                                                    TreeNode address107 = FAILURE;
                                                                    String chunk21 = null;
                                                                    if (offset < inputSize) {
                                                                        chunk21 = input.substring(offset, Math.min(offset + 1, input.length()));
                                                                    }
                                                                    if (chunk21 != null && chunk21.equals(":")) {
                                                                        address107 = new TreeNode(input.substring(offset, offset + 1), offset);
                                                                        offset = offset + 1;
                                                                    } else {
                                                                        address107 = FAILURE;
                                                                        if (offset > failure) {
                                                                            failure = offset;
                                                                            expected = new ArrayList<String>();
                                                                        }
                                                                        if (offset == failure) {
                                                                            expected.add("\":\"");
                                                                        }
                                                                    }
                                                                    if (address107 != FAILURE) {
                                                                        elements40.add(4, address107);
                                                                        TreeNode address108 = FAILURE;
                                                                        int remaining32 = 0;
                                                                        int index56 = offset;
                                                                        List<TreeNode> elements43 = new ArrayList<TreeNode>();
                                                                        TreeNode address109 = new TreeNode("", -1);
                                                                        while (address109 != FAILURE) {
                                                                            address109 = _read_ws();
                                                                            if (address109 != FAILURE) {
                                                                                elements43.add(address109);
                                                                                --remaining32;
                                                                            }
                                                                        }
                                                                        if (remaining32 <= 0) {
                                                                            address108 = new TreeNode(input.substring(index56, offset), index56, elements43);
                                                                            offset = offset;
                                                                        } else {
                                                                            address108 = FAILURE;
                                                                        }
                                                                        if (address108 != FAILURE) {
                                                                            elements40.add(5, address108);
                                                                            TreeNode address110 = FAILURE;
                                                                            address110 = _read_decimal();
                                                                            if (address110 != FAILURE) {
                                                                                elements40.add(6, address110);
                                                                                TreeNode address111 = FAILURE;
                                                                                int remaining33 = 0;
                                                                                int index57 = offset;
                                                                                List<TreeNode> elements44 = new ArrayList<TreeNode>();
                                                                                TreeNode address112 = new TreeNode("", -1);
                                                                                while (address112 != FAILURE) {
                                                                                    address112 = _read_ws();
                                                                                    if (address112 != FAILURE) {
                                                                                        elements44.add(address112);
                                                                                        --remaining33;
                                                                                    }
                                                                                }
                                                                                if (remaining33 <= 0) {
                                                                                    address111 = new TreeNode(input.substring(index57, offset), index57, elements44);
                                                                                    offset = offset;
                                                                                } else {
                                                                                    address111 = FAILURE;
                                                                                }
                                                                                if (address111 != FAILURE) {
                                                                                    elements40.add(7, address111);
                                                                                    TreeNode address113 = FAILURE;
                                                                                    int index58 = offset;
                                                                                    String chunk22 = null;
                                                                                    if (offset < inputSize) {
                                                                                        chunk22 = input.substring(offset, Math.min(offset + 2, input.length()));
                                                                                    }
                                                                                    if (chunk22 != null && chunk22.equals("TO")) {
                                                                                        address113 = new TreeNode(input.substring(offset, offset + 2), offset);
                                                                                        offset = offset + 2;
                                                                                    } else {
                                                                                        address113 = FAILURE;
                                                                                        if (offset > failure) {
                                                                                            failure = offset;
                                                                                            expected = new ArrayList<String>();
                                                                                        }
                                                                                        if (offset == failure) {
                                                                                            expected.add("\"TO\"");
                                                                                        }
                                                                                    }
                                                                                    if (address113 == FAILURE) {
                                                                                        offset = index58;
                                                                                        String chunk23 = null;
                                                                                        if (offset < inputSize) {
                                                                                            chunk23 = input.substring(offset, Math.min(offset + 2, input.length()));
                                                                                        }
                                                                                        if (chunk23 != null && chunk23.equals("to")) {
                                                                                            address113 = new TreeNode(input.substring(offset, offset + 2), offset);
                                                                                            offset = offset + 2;
                                                                                        } else {
                                                                                            address113 = FAILURE;
                                                                                            if (offset > failure) {
                                                                                                failure = offset;
                                                                                                expected = new ArrayList<String>();
                                                                                            }
                                                                                            if (offset == failure) {
                                                                                                expected.add("\"to\"");
                                                                                            }
                                                                                        }
                                                                                        if (address113 == FAILURE) {
                                                                                            offset = index58;
                                                                                        }
                                                                                    }
                                                                                    if (address113 != FAILURE) {
                                                                                        elements40.add(8, address113);
                                                                                        TreeNode address114 = FAILURE;
                                                                                        int remaining34 = 0;
                                                                                        int index59 = offset;
                                                                                        List<TreeNode> elements45 = new ArrayList<TreeNode>();
                                                                                        TreeNode address115 = new TreeNode("", -1);
                                                                                        while (address115 != FAILURE) {
                                                                                            address115 = _read_ws();
                                                                                            if (address115 != FAILURE) {
                                                                                                elements45.add(address115);
                                                                                                --remaining34;
                                                                                            }
                                                                                        }
                                                                                        if (remaining34 <= 0) {
                                                                                            address114 = new TreeNode(input.substring(index59, offset), index59, elements45);
                                                                                            offset = offset;
                                                                                        } else {
                                                                                            address114 = FAILURE;
                                                                                        }
                                                                                        if (address114 != FAILURE) {
                                                                                            elements40.add(9, address114);
                                                                                            TreeNode address116 = FAILURE;
                                                                                            address116 = _read_decimal();
                                                                                            if (address116 != FAILURE) {
                                                                                                elements40.add(10, address116);
                                                                                            } else {
                                                                                                elements40 = null;
                                                                                                offset = index52;
                                                                                            }
                                                                                        } else {
                                                                                            elements40 = null;
                                                                                            offset = index52;
                                                                                        }
                                                                                    } else {
                                                                                        elements40 = null;
                                                                                        offset = index52;
                                                                                    }
                                                                                } else {
                                                                                    elements40 = null;
                                                                                    offset = index52;
                                                                                }
                                                                            } else {
                                                                                elements40 = null;
                                                                                offset = index52;
                                                                            }
                                                                        } else {
                                                                            elements40 = null;
                                                                            offset = index52;
                                                                        }
                                                                    } else {
                                                                        elements40 = null;
                                                                        offset = index52;
                                                                    }
                                                                } else {
                                                                    elements40 = null;
                                                                    offset = index52;
                                                                }
                                                            } else {
                                                                elements40 = null;
                                                                offset = index52;
                                                            }
                                                        } else {
                                                            elements40 = null;
                                                            offset = index52;
                                                        }
                                                    } else {
                                                        elements40 = null;
                                                        offset = index52;
                                                    }
                                                    if (elements40 == null) {
                                                        address0 = FAILURE;
                                                    } else {
                                                        address0 = actions.make_numeric_range_token(input, index52, offset, elements40);
                                                        offset = offset;
                                                    }
                                                    if (address0 == FAILURE) {
                                                        offset = index1;
                                                        int index60 = offset;
                                                        List<TreeNode> elements46 = new ArrayList<TreeNode>(7);
                                                        TreeNode address117 = FAILURE;
                                                        int index61 = offset;
                                                        String chunk24 = null;
                                                        if (offset < inputSize) {
                                                            chunk24 = input.substring(offset, Math.min(offset + 3, input.length()));
                                                        }
                                                        if (chunk24 != null && chunk24.equals("NOT")) {
                                                            address117 = new TreeNode(input.substring(offset, offset + 3), offset);
                                                            offset = offset + 3;
                                                        } else {
                                                            address117 = FAILURE;
                                                            if (offset > failure) {
                                                                failure = offset;
                                                                expected = new ArrayList<String>();
                                                            }
                                                            if (offset == failure) {
                                                                expected.add("\"NOT\"");
                                                            }
                                                        }
                                                        if (address117 == FAILURE) {
                                                            address117 = new TreeNode(input.substring(index61, index61), index61);
                                                            offset = index61;
                                                        }
                                                        if (address117 != FAILURE) {
                                                            elements46.add(0, address117);
                                                            TreeNode address118 = FAILURE;
                                                            int remaining35 = 0;
                                                            int index62 = offset;
                                                            List<TreeNode> elements47 = new ArrayList<TreeNode>();
                                                            TreeNode address119 = new TreeNode("", -1);
                                                            while (address119 != FAILURE) {
                                                                address119 = _read_ws();
                                                                if (address119 != FAILURE) {
                                                                    elements47.add(address119);
                                                                    --remaining35;
                                                                }
                                                            }
                                                            if (remaining35 <= 0) {
                                                                address118 = new TreeNode(input.substring(index62, offset), index62, elements47);
                                                                offset = offset;
                                                            } else {
                                                                address118 = FAILURE;
                                                            }
                                                            if (address118 != FAILURE) {
                                                                elements46.add(1, address118);
                                                                TreeNode address120 = FAILURE;
                                                                address120 = _read_alphanumeric();
                                                                if (address120 != FAILURE) {
                                                                    elements46.add(2, address120);
                                                                    TreeNode address121 = FAILURE;
                                                                    int remaining36 = 0;
                                                                    int index63 = offset;
                                                                    List<TreeNode> elements48 = new ArrayList<TreeNode>();
                                                                    TreeNode address122 = new TreeNode("", -1);
                                                                    while (address122 != FAILURE) {
                                                                        address122 = _read_ws();
                                                                        if (address122 != FAILURE) {
                                                                            elements48.add(address122);
                                                                            --remaining36;
                                                                        }
                                                                    }
                                                                    if (remaining36 <= 0) {
                                                                        address121 = new TreeNode(input.substring(index63, offset), index63, elements48);
                                                                        offset = offset;
                                                                    } else {
                                                                        address121 = FAILURE;
                                                                    }
                                                                    if (address121 != FAILURE) {
                                                                        elements46.add(3, address121);
                                                                        TreeNode address123 = FAILURE;
                                                                        String chunk25 = null;
                                                                        if (offset < inputSize) {
                                                                            chunk25 = input.substring(offset, Math.min(offset + 1, input.length()));
                                                                        }
                                                                        if (chunk25 != null && chunk25.equals(":")) {
                                                                            address123 = new TreeNode(input.substring(offset, offset + 1), offset);
                                                                            offset = offset + 1;
                                                                        } else {
                                                                            address123 = FAILURE;
                                                                            if (offset > failure) {
                                                                                failure = offset;
                                                                                expected = new ArrayList<String>();
                                                                            }
                                                                            if (offset == failure) {
                                                                                expected.add("\":\"");
                                                                            }
                                                                        }
                                                                        if (address123 != FAILURE) {
                                                                            elements46.add(4, address123);
                                                                            TreeNode address124 = FAILURE;
                                                                            int remaining37 = 0;
                                                                            int index64 = offset;
                                                                            List<TreeNode> elements49 = new ArrayList<TreeNode>();
                                                                            TreeNode address125 = new TreeNode("", -1);
                                                                            while (address125 != FAILURE) {
                                                                                address125 = _read_ws();
                                                                                if (address125 != FAILURE) {
                                                                                    elements49.add(address125);
                                                                                    --remaining37;
                                                                                }
                                                                            }
                                                                            if (remaining37 <= 0) {
                                                                                address124 = new TreeNode(input.substring(index64, offset), index64, elements49);
                                                                                offset = offset;
                                                                            } else {
                                                                                address124 = FAILURE;
                                                                            }
                                                                            if (address124 != FAILURE) {
                                                                                elements46.add(5, address124);
                                                                                TreeNode address126 = FAILURE;
                                                                                address126 = _read_alphanumeric();
                                                                                if (address126 != FAILURE) {
                                                                                    elements46.add(6, address126);
                                                                                } else {
                                                                                    elements46 = null;
                                                                                    offset = index60;
                                                                                }
                                                                            } else {
                                                                                elements46 = null;
                                                                                offset = index60;
                                                                            }
                                                                        } else {
                                                                            elements46 = null;
                                                                            offset = index60;
                                                                        }
                                                                    } else {
                                                                        elements46 = null;
                                                                        offset = index60;
                                                                    }
                                                                } else {
                                                                    elements46 = null;
                                                                    offset = index60;
                                                                }
                                                            } else {
                                                                elements46 = null;
                                                                offset = index60;
                                                            }
                                                        } else {
                                                            elements46 = null;
                                                            offset = index60;
                                                        }
                                                        if (elements46 == null) {
                                                            address0 = FAILURE;
                                                        } else {
                                                            address0 = actions.make_string_token(input, index60, offset, elements46);
                                                            offset = offset;
                                                        }
                                                        if (address0 == FAILURE) {
                                                            offset = index1;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_app_version() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.app_version);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.app_version, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(4);
            TreeNode address1 = FAILURE;
            int remaining0 = 1;
            int index2 = offset;
            List<TreeNode> elements1 = new ArrayList<TreeNode>();
            TreeNode address2 = new TreeNode("", -1);
            while (address2 != FAILURE) {
                String chunk0 = null;
                if (offset < inputSize) {
                    chunk0 = input.substring(offset, Math.min(offset + 1, input.length()));
                }
                if (chunk0 != null && REGEX_1.matcher(chunk0).matches()) {
                    address2 = new TreeNode(input.substring(offset, offset + 1), offset);
                    offset = offset + 1;
                } else {
                    address2 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String>();
                    }
                    if (offset == failure) {
                        expected.add("[0-9]");
                    }
                }
                if (address2 != FAILURE) {
                    elements1.add(address2);
                    --remaining0;
                }
            }
            if (remaining0 <= 0) {
                address1 = new TreeNode(input.substring(index2, offset), index2, elements1);
                offset = offset;
            } else {
                address1 = FAILURE;
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address3 = FAILURE;
                String chunk1 = null;
                if (offset < inputSize) {
                    chunk1 = input.substring(offset, Math.min(offset + 1, input.length()));
                }
                if (chunk1 != null && chunk1.equals(".")) {
                    address3 = new TreeNode(input.substring(offset, offset + 1), offset);
                    offset = offset + 1;
                } else {
                    address3 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String>();
                    }
                    if (offset == failure) {
                        expected.add("\".\"");
                    }
                }
                if (address3 != FAILURE) {
                    elements0.add(1, address3);
                    TreeNode address4 = FAILURE;
                    int remaining1 = 1;
                    int index3 = offset;
                    List<TreeNode> elements2 = new ArrayList<TreeNode>();
                    TreeNode address5 = new TreeNode("", -1);
                    while (address5 != FAILURE) {
                        String chunk2 = null;
                        if (offset < inputSize) {
                            chunk2 = input.substring(offset, Math.min(offset + 1, input.length()));
                        }
                        if (chunk2 != null && REGEX_2.matcher(chunk2).matches()) {
                            address5 = new TreeNode(input.substring(offset, offset + 1), offset);
                            offset = offset + 1;
                        } else {
                            address5 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String>();
                            }
                            if (offset == failure) {
                                expected.add("[0-9]");
                            }
                        }
                        if (address5 != FAILURE) {
                            elements2.add(address5);
                            --remaining1;
                        }
                    }
                    if (remaining1 <= 0) {
                        address4 = new TreeNode(input.substring(index3, offset), index3, elements2);
                        offset = offset;
                    } else {
                        address4 = FAILURE;
                    }
                    if (address4 != FAILURE) {
                        elements0.add(2, address4);
                        TreeNode address6 = FAILURE;
                        int remaining2 = 1;
                        int index4 = offset;
                        List<TreeNode> elements3 = new ArrayList<TreeNode>();
                        TreeNode address7 = new TreeNode("", -1);
                        while (address7 != FAILURE) {
                            address7 = _read_app_version_token();
                            if (address7 != FAILURE) {
                                elements3.add(address7);
                                --remaining2;
                            }
                        }
                        if (remaining2 <= 0) {
                            address6 = new TreeNode(input.substring(index4, offset), index4, elements3);
                            offset = offset;
                        } else {
                            address6 = FAILURE;
                        }
                        if (address6 != FAILURE) {
                            elements0.add(3, address6);
                        } else {
                            elements0 = null;
                            offset = index1;
                        }
                    } else {
                        elements0 = null;
                        offset = index1;
                    }
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_app_version_token() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.app_version_token);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.app_version_token, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(2);
            TreeNode address1 = FAILURE;
            String chunk0 = null;
            if (offset < inputSize) {
                chunk0 = input.substring(offset, Math.min(offset + 1, input.length()));
            }
            if (chunk0 != null && chunk0.equals(".")) {
                address1 = new TreeNode(input.substring(offset, offset + 1), offset);
                offset = offset + 1;
            } else {
                address1 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String>();
                }
                if (offset == failure) {
                    expected.add("\".\"");
                }
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address2 = FAILURE;
                int remaining0 = 1;
                int index2 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>();
                TreeNode address3 = new TreeNode("", -1);
                while (address3 != FAILURE) {
                    String chunk1 = null;
                    if (offset < inputSize) {
                        chunk1 = input.substring(offset, Math.min(offset + 1, input.length()));
                    }
                    if (chunk1 != null && REGEX_3.matcher(chunk1).matches()) {
                        address3 = new TreeNode(input.substring(offset, offset + 1), offset);
                        offset = offset + 1;
                    } else {
                        address3 = FAILURE;
                        if (offset > failure) {
                            failure = offset;
                            expected = new ArrayList<String>();
                        }
                        if (offset == failure) {
                            expected.add("[0-9]");
                        }
                    }
                    if (address3 != FAILURE) {
                        elements1.add(address3);
                        --remaining0;
                    }
                }
                if (remaining0 <= 0) {
                    address2 = new TreeNode(input.substring(index2, offset), index2, elements1);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements0.add(1, address2);
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_alphanumeric() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.alphanumeric);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.alphanumeric, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            int remaining0 = 1;
            int index2 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>();
            TreeNode address1 = new TreeNode("", -1);
            while (address1 != FAILURE) {
                String chunk0 = null;
                if (offset < inputSize) {
                    chunk0 = input.substring(offset, Math.min(offset + 1, input.length()));
                }
                if (chunk0 != null && REGEX_4.matcher(chunk0).matches()) {
                    address1 = new TreeNode(input.substring(offset, offset + 1), offset);
                    offset = offset + 1;
                } else {
                    address1 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String>();
                    }
                    if (offset == failure) {
                        expected.add("[a-zA-Z0-9_.]");
                    }
                }
                if (address1 != FAILURE) {
                    elements0.add(address1);
                    --remaining0;
                }
            }
            if (remaining0 <= 0) {
                address0 = new TreeNode(input.substring(index2, offset), index2, elements0);
                offset = offset;
            } else {
                address0 = FAILURE;
            }
            if (address0 == FAILURE) {
                offset = index1;
                int index3 = offset;
                List<TreeNode> elements1 = new ArrayList<TreeNode>(3);
                TreeNode address2 = FAILURE;
                int remaining1 = 1;
                int index4 = offset;
                List<TreeNode> elements2 = new ArrayList<TreeNode>();
                TreeNode address3 = new TreeNode("", -1);
                while (address3 != FAILURE) {
                    String chunk1 = null;
                    if (offset < inputSize) {
                        chunk1 = input.substring(offset, Math.min(offset + 1, input.length()));
                    }
                    if (chunk1 != null && REGEX_5.matcher(chunk1).matches()) {
                        address3 = new TreeNode(input.substring(offset, offset + 1), offset);
                        offset = offset + 1;
                    } else {
                        address3 = FAILURE;
                        if (offset > failure) {
                            failure = offset;
                            expected = new ArrayList<String>();
                        }
                        if (offset == failure) {
                            expected.add("[\"]");
                        }
                    }
                    if (address3 != FAILURE) {
                        elements2.add(address3);
                        --remaining1;
                    }
                }
                if (remaining1 <= 0) {
                    address2 = new TreeNode(input.substring(index4, offset), index4, elements2);
                    offset = offset;
                } else {
                    address2 = FAILURE;
                }
                if (address2 != FAILURE) {
                    elements1.add(0, address2);
                    TreeNode address4 = FAILURE;
                    int remaining2 = 1;
                    int index5 = offset;
                    List<TreeNode> elements3 = new ArrayList<TreeNode>();
                    TreeNode address5 = new TreeNode("", -1);
                    while (address5 != FAILURE) {
                        String chunk2 = null;
                        if (offset < inputSize) {
                            chunk2 = input.substring(offset, Math.min(offset + 1, input.length()));
                        }
                        if (chunk2 != null && REGEX_6.matcher(chunk2).matches()) {
                            address5 = new TreeNode(input.substring(offset, offset + 1), offset);
                            offset = offset + 1;
                        } else {
                            address5 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String>();
                            }
                            if (offset == failure) {
                                expected.add("[a-zA-Z0-9_.' \\t]");
                            }
                        }
                        if (address5 != FAILURE) {
                            elements3.add(address5);
                            --remaining2;
                        }
                    }
                    if (remaining2 <= 0) {
                        address4 = new TreeNode(input.substring(index5, offset), index5, elements3);
                        offset = offset;
                    } else {
                        address4 = FAILURE;
                    }
                    if (address4 != FAILURE) {
                        elements1.add(1, address4);
                        TreeNode address6 = FAILURE;
                        int remaining3 = 1;
                        int index6 = offset;
                        List<TreeNode> elements4 = new ArrayList<TreeNode>();
                        TreeNode address7 = new TreeNode("", -1);
                        while (address7 != FAILURE) {
                            String chunk3 = null;
                            if (offset < inputSize) {
                                chunk3 = input.substring(offset, Math.min(offset + 1, input.length()));
                            }
                            if (chunk3 != null && REGEX_7.matcher(chunk3).matches()) {
                                address7 = new TreeNode(input.substring(offset, offset + 1), offset);
                                offset = offset + 1;
                            } else {
                                address7 = FAILURE;
                                if (offset > failure) {
                                    failure = offset;
                                    expected = new ArrayList<String>();
                                }
                                if (offset == failure) {
                                    expected.add("[\"]");
                                }
                            }
                            if (address7 != FAILURE) {
                                elements4.add(address7);
                                --remaining3;
                            }
                        }
                        if (remaining3 <= 0) {
                            address6 = new TreeNode(input.substring(index6, offset), index6, elements4);
                            offset = offset;
                        } else {
                            address6 = FAILURE;
                        }
                        if (address6 != FAILURE) {
                            elements1.add(2, address6);
                        } else {
                            elements1 = null;
                            offset = index3;
                        }
                    } else {
                        elements1 = null;
                        offset = index3;
                    }
                } else {
                    elements1 = null;
                    offset = index3;
                }
                if (elements1 == null) {
                    address0 = FAILURE;
                } else {
                    address0 = new TreeNode(input.substring(index3, offset), index3, elements1);
                    offset = offset;
                }
                if (address0 == FAILURE) {
                    offset = index1;
                    int index7 = offset;
                    List<TreeNode> elements5 = new ArrayList<TreeNode>(3);
                    TreeNode address8 = FAILURE;
                    int remaining4 = 1;
                    int index8 = offset;
                    List<TreeNode> elements6 = new ArrayList<TreeNode>();
                    TreeNode address9 = new TreeNode("", -1);
                    while (address9 != FAILURE) {
                        String chunk4 = null;
                        if (offset < inputSize) {
                            chunk4 = input.substring(offset, Math.min(offset + 1, input.length()));
                        }
                        if (chunk4 != null && REGEX_8.matcher(chunk4).matches()) {
                            address9 = new TreeNode(input.substring(offset, offset + 1), offset);
                            offset = offset + 1;
                        } else {
                            address9 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String>();
                            }
                            if (offset == failure) {
                                expected.add("[']");
                            }
                        }
                        if (address9 != FAILURE) {
                            elements6.add(address9);
                            --remaining4;
                        }
                    }
                    if (remaining4 <= 0) {
                        address8 = new TreeNode(input.substring(index8, offset), index8, elements6);
                        offset = offset;
                    } else {
                        address8 = FAILURE;
                    }
                    if (address8 != FAILURE) {
                        elements5.add(0, address8);
                        TreeNode address10 = FAILURE;
                        int remaining5 = 1;
                        int index9 = offset;
                        List<TreeNode> elements7 = new ArrayList<TreeNode>();
                        TreeNode address11 = new TreeNode("", -1);
                        while (address11 != FAILURE) {
                            String chunk5 = null;
                            if (offset < inputSize) {
                                chunk5 = input.substring(offset, Math.min(offset + 1, input.length()));
                            }
                            if (chunk5 != null && REGEX_9.matcher(chunk5).matches()) {
                                address11 = new TreeNode(input.substring(offset, offset + 1), offset);
                                offset = offset + 1;
                            } else {
                                address11 = FAILURE;
                                if (offset > failure) {
                                    failure = offset;
                                    expected = new ArrayList<String>();
                                }
                                if (offset == failure) {
                                    expected.add("[a-zA-Z0-9_.\" \\t]");
                                }
                            }
                            if (address11 != FAILURE) {
                                elements7.add(address11);
                                --remaining5;
                            }
                        }
                        if (remaining5 <= 0) {
                            address10 = new TreeNode(input.substring(index9, offset), index9, elements7);
                            offset = offset;
                        } else {
                            address10 = FAILURE;
                        }
                        if (address10 != FAILURE) {
                            elements5.add(1, address10);
                            TreeNode address12 = FAILURE;
                            int remaining6 = 1;
                            int index10 = offset;
                            List<TreeNode> elements8 = new ArrayList<TreeNode>();
                            TreeNode address13 = new TreeNode("", -1);
                            while (address13 != FAILURE) {
                                String chunk6 = null;
                                if (offset < inputSize) {
                                    chunk6 = input.substring(offset, Math.min(offset + 1, input.length()));
                                }
                                if (chunk6 != null && REGEX_10.matcher(chunk6).matches()) {
                                    address13 = new TreeNode(input.substring(offset, offset + 1), offset);
                                    offset = offset + 1;
                                } else {
                                    address13 = FAILURE;
                                    if (offset > failure) {
                                        failure = offset;
                                        expected = new ArrayList<String>();
                                    }
                                    if (offset == failure) {
                                        expected.add("[']");
                                    }
                                }
                                if (address13 != FAILURE) {
                                    elements8.add(address13);
                                    --remaining6;
                                }
                            }
                            if (remaining6 <= 0) {
                                address12 = new TreeNode(input.substring(index10, offset), index10, elements8);
                                offset = offset;
                            } else {
                                address12 = FAILURE;
                            }
                            if (address12 != FAILURE) {
                                elements5.add(2, address12);
                            } else {
                                elements5 = null;
                                offset = index7;
                            }
                        } else {
                            elements5 = null;
                            offset = index7;
                        }
                    } else {
                        elements5 = null;
                        offset = index7;
                    }
                    if (elements5 == null) {
                        address0 = FAILURE;
                    } else {
                        address0 = new TreeNode(input.substring(index7, offset), index7, elements5);
                        offset = offset;
                    }
                    if (address0 == FAILURE) {
                        offset = index1;
                    }
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_decimal() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.decimal);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.decimal, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            List<TreeNode> elements0 = new ArrayList<TreeNode>(3);
            TreeNode address1 = FAILURE;
            int remaining0 = 1;
            int index2 = offset;
            List<TreeNode> elements1 = new ArrayList<TreeNode>();
            TreeNode address2 = new TreeNode("", -1);
            while (address2 != FAILURE) {
                String chunk0 = null;
                if (offset < inputSize) {
                    chunk0 = input.substring(offset, Math.min(offset + 1, input.length()));
                }
                if (chunk0 != null && REGEX_11.matcher(chunk0).matches()) {
                    address2 = new TreeNode(input.substring(offset, offset + 1), offset);
                    offset = offset + 1;
                } else {
                    address2 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String>();
                    }
                    if (offset == failure) {
                        expected.add("[0-9]");
                    }
                }
                if (address2 != FAILURE) {
                    elements1.add(address2);
                    --remaining0;
                }
            }
            if (remaining0 <= 0) {
                address1 = new TreeNode(input.substring(index2, offset), index2, elements1);
                offset = offset;
            } else {
                address1 = FAILURE;
            }
            if (address1 != FAILURE) {
                elements0.add(0, address1);
                TreeNode address3 = FAILURE;
                int index3 = offset;
                String chunk1 = null;
                if (offset < inputSize) {
                    chunk1 = input.substring(offset, Math.min(offset + 1, input.length()));
                }
                if (chunk1 != null && chunk1.equals(".")) {
                    address3 = new TreeNode(input.substring(offset, offset + 1), offset);
                    offset = offset + 1;
                } else {
                    address3 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String>();
                    }
                    if (offset == failure) {
                        expected.add("\".\"");
                    }
                }
                if (address3 == FAILURE) {
                    address3 = new TreeNode(input.substring(index3, index3), index3);
                    offset = index3;
                }
                if (address3 != FAILURE) {
                    elements0.add(1, address3);
                    TreeNode address4 = FAILURE;
                    int remaining1 = 0;
                    int index4 = offset;
                    List<TreeNode> elements2 = new ArrayList<TreeNode>();
                    TreeNode address5 = new TreeNode("", -1);
                    while (address5 != FAILURE) {
                        String chunk2 = null;
                        if (offset < inputSize) {
                            chunk2 = input.substring(offset, Math.min(offset + 1, input.length()));
                        }
                        if (chunk2 != null && REGEX_12.matcher(chunk2).matches()) {
                            address5 = new TreeNode(input.substring(offset, offset + 1), offset);
                            offset = offset + 1;
                        } else {
                            address5 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String>();
                            }
                            if (offset == failure) {
                                expected.add("[0-9]");
                            }
                        }
                        if (address5 != FAILURE) {
                            elements2.add(address5);
                            --remaining1;
                        }
                    }
                    if (remaining1 <= 0) {
                        address4 = new TreeNode(input.substring(index4, offset), index4, elements2);
                        offset = offset;
                    } else {
                        address4 = FAILURE;
                    }
                    if (address4 != FAILURE) {
                        elements0.add(2, address4);
                    } else {
                        elements0 = null;
                        offset = index1;
                    }
                } else {
                    elements0 = null;
                    offset = index1;
                }
            } else {
                elements0 = null;
                offset = index1;
            }
            if (elements0 == null) {
                address0 = FAILURE;
            } else {
                address0 = new TreeNode(input.substring(index1, offset), index1, elements0);
                offset = offset;
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_or() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.or);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.or, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            String chunk0 = null;
            if (offset < inputSize) {
                chunk0 = input.substring(offset, Math.min(offset + 2, input.length()));
            }
            if (chunk0 != null && chunk0.equals("OR")) {
                address0 = new TreeNode(input.substring(offset, offset + 2), offset);
                offset = offset + 2;
            } else {
                address0 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String>();
                }
                if (offset == failure) {
                    expected.add("\"OR\"");
                }
            }
            if (address0 == FAILURE) {
                offset = index1;
                String chunk1 = null;
                if (offset < inputSize) {
                    chunk1 = input.substring(offset, Math.min(offset + 2, input.length()));
                }
                if (chunk1 != null && chunk1.equals("or")) {
                    address0 = new TreeNode(input.substring(offset, offset + 2), offset);
                    offset = offset + 2;
                } else {
                    address0 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String>();
                    }
                    if (offset == failure) {
                        expected.add("\"or\"");
                    }
                }
                if (address0 == FAILURE) {
                    offset = index1;
                    String chunk2 = null;
                    if (offset < inputSize) {
                        chunk2 = input.substring(offset, Math.min(offset + 1, input.length()));
                    }
                    if (chunk2 != null && chunk2.equals("|")) {
                        address0 = new TreeNode(input.substring(offset, offset + 1), offset);
                        offset = offset + 1;
                    } else {
                        address0 = FAILURE;
                        if (offset > failure) {
                            failure = offset;
                            expected = new ArrayList<String>();
                        }
                        if (offset == failure) {
                            expected.add("\"|\"");
                        }
                    }
                    if (address0 == FAILURE) {
                        offset = index1;
                        String chunk3 = null;
                        if (offset < inputSize) {
                            chunk3 = input.substring(offset, Math.min(offset + 2, input.length()));
                        }
                        if (chunk3 != null && chunk3.equals("||")) {
                            address0 = new TreeNode(input.substring(offset, offset + 2), offset);
                            offset = offset + 2;
                        } else {
                            address0 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String>();
                            }
                            if (offset == failure) {
                                expected.add("\"||\"");
                            }
                        }
                        if (address0 == FAILURE) {
                            offset = index1;
                        }
                    }
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_and() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.and);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.and, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            String chunk0 = null;
            if (offset < inputSize) {
                chunk0 = input.substring(offset, Math.min(offset + 3, input.length()));
            }
            if (chunk0 != null && chunk0.equals("AND")) {
                address0 = new TreeNode(input.substring(offset, offset + 3), offset);
                offset = offset + 3;
            } else {
                address0 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String>();
                }
                if (offset == failure) {
                    expected.add("\"AND\"");
                }
            }
            if (address0 == FAILURE) {
                offset = index1;
                String chunk1 = null;
                if (offset < inputSize) {
                    chunk1 = input.substring(offset, Math.min(offset + 3, input.length()));
                }
                if (chunk1 != null && chunk1.equals("and")) {
                    address0 = new TreeNode(input.substring(offset, offset + 3), offset);
                    offset = offset + 3;
                } else {
                    address0 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String>();
                    }
                    if (offset == failure) {
                        expected.add("\"and\"");
                    }
                }
                if (address0 == FAILURE) {
                    offset = index1;
                    String chunk2 = null;
                    if (offset < inputSize) {
                        chunk2 = input.substring(offset, Math.min(offset + 1, input.length()));
                    }
                    if (chunk2 != null && chunk2.equals("&")) {
                        address0 = new TreeNode(input.substring(offset, offset + 1), offset);
                        offset = offset + 1;
                    } else {
                        address0 = FAILURE;
                        if (offset > failure) {
                            failure = offset;
                            expected = new ArrayList<String>();
                        }
                        if (offset == failure) {
                            expected.add("\"&\"");
                        }
                    }
                    if (address0 == FAILURE) {
                        offset = index1;
                        String chunk3 = null;
                        if (offset < inputSize) {
                            chunk3 = input.substring(offset, Math.min(offset + 2, input.length()));
                        }
                        if (chunk3 != null && chunk3.equals("&&")) {
                            address0 = new TreeNode(input.substring(offset, offset + 2), offset);
                            offset = offset + 2;
                        } else {
                            address0 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String>();
                            }
                            if (offset == failure) {
                                expected.add("\"&&\"");
                            }
                        }
                        if (address0 == FAILURE) {
                            offset = index1;
                        }
                    }
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_operator() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.operator);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.operator, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            int index1 = offset;
            String chunk0 = null;
            if (offset < inputSize) {
                chunk0 = input.substring(offset, Math.min(offset + 1, input.length()));
            }
            if (chunk0 != null && chunk0.equals("=")) {
                address0 = new TreeNode(input.substring(offset, offset + 1), offset);
                offset = offset + 1;
            } else {
                address0 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String>();
                }
                if (offset == failure) {
                    expected.add("\"=\"");
                }
            }
            if (address0 == FAILURE) {
                offset = index1;
                String chunk1 = null;
                if (offset < inputSize) {
                    chunk1 = input.substring(offset, Math.min(offset + 1, input.length()));
                }
                if (chunk1 != null && chunk1.equals(">")) {
                    address0 = new TreeNode(input.substring(offset, offset + 1), offset);
                    offset = offset + 1;
                } else {
                    address0 = FAILURE;
                    if (offset > failure) {
                        failure = offset;
                        expected = new ArrayList<String>();
                    }
                    if (offset == failure) {
                        expected.add("\">\"");
                    }
                }
                if (address0 == FAILURE) {
                    offset = index1;
                    String chunk2 = null;
                    if (offset < inputSize) {
                        chunk2 = input.substring(offset, Math.min(offset + 2, input.length()));
                    }
                    if (chunk2 != null && chunk2.equals(">=")) {
                        address0 = new TreeNode(input.substring(offset, offset + 2), offset);
                        offset = offset + 2;
                    } else {
                        address0 = FAILURE;
                        if (offset > failure) {
                            failure = offset;
                            expected = new ArrayList<String>();
                        }
                        if (offset == failure) {
                            expected.add("\">=\"");
                        }
                    }
                    if (address0 == FAILURE) {
                        offset = index1;
                        String chunk3 = null;
                        if (offset < inputSize) {
                            chunk3 = input.substring(offset, Math.min(offset + 1, input.length()));
                        }
                        if (chunk3 != null && chunk3.equals("<")) {
                            address0 = new TreeNode(input.substring(offset, offset + 1), offset);
                            offset = offset + 1;
                        } else {
                            address0 = FAILURE;
                            if (offset > failure) {
                                failure = offset;
                                expected = new ArrayList<String>();
                            }
                            if (offset == failure) {
                                expected.add("\"<\"");
                            }
                        }
                        if (address0 == FAILURE) {
                            offset = index1;
                            String chunk4 = null;
                            if (offset < inputSize) {
                                chunk4 = input.substring(offset, Math.min(offset + 2, input.length()));
                            }
                            if (chunk4 != null && chunk4.equals("<=")) {
                                address0 = new TreeNode(input.substring(offset, offset + 2), offset);
                                offset = offset + 2;
                            } else {
                                address0 = FAILURE;
                                if (offset > failure) {
                                    failure = offset;
                                    expected = new ArrayList<String>();
                                }
                                if (offset == failure) {
                                    expected.add("\"<=\"");
                                }
                            }
                            if (address0 == FAILURE) {
                                offset = index1;
                                String chunk5 = null;
                                if (offset < inputSize) {
                                    chunk5 = input.substring(offset, Math.min(offset + 2, input.length()));
                                }
                                if (chunk5 != null && chunk5.equals("!=")) {
                                    address0 = new TreeNode(input.substring(offset, offset + 2), offset);
                                    offset = offset + 2;
                                } else {
                                    address0 = FAILURE;
                                    if (offset > failure) {
                                        failure = offset;
                                        expected = new ArrayList<String>();
                                    }
                                    if (offset == failure) {
                                        expected.add("\"!=\"");
                                    }
                                }
                                if (address0 == FAILURE) {
                                    offset = index1;
                                }
                            }
                        }
                    }
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }

    TreeNode _read_ws() {
        TreeNode address0 = FAILURE;
        int index0 = offset;
        Map<Integer, CacheRecord> rule = cache.get(Label.ws);
        if (rule == null) {
            rule = new HashMap<Integer, CacheRecord>();
            cache.put(Label.ws, rule);
        }
        if (rule.containsKey(offset)) {
            address0 = rule.get(offset).node;
            offset = rule.get(offset).tail;
        } else {
            String chunk0 = null;
            if (offset < inputSize) {
                chunk0 = input.substring(offset, Math.min(offset + 1, input.length()));
            }
            if (chunk0 != null && REGEX_13.matcher(chunk0).matches()) {
                address0 = new TreeNode(input.substring(offset, offset + 1), offset);
                offset = offset + 1;
            } else {
                address0 = FAILURE;
                if (offset > failure) {
                    failure = offset;
                    expected = new ArrayList<String>();
                }
                if (offset == failure) {
                    expected.add("[ \\t]");
                }
            }
            rule.put(index0, new CacheRecord(address0, offset));
        }
        return address0;
    }
}
