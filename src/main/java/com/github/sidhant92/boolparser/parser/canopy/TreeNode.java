package com.github.sidhant92.boolparser.parser.canopy;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TreeNode implements Iterable<TreeNode> {
    public String text;
    public int offset;
    public List<TreeNode> elements;

    Map<Label, TreeNode> labelled;

    public TreeNode() {
        this("", -1, new ArrayList<TreeNode>(0));
    }

    public TreeNode(String text, int offset) {
        this(text, offset, new ArrayList<TreeNode>(0));
    }

    public TreeNode(String text, int offset, List<TreeNode> elements) {
        this.text = text;
        this.offset = offset;
        this.elements = elements;
        this.labelled = new EnumMap<Label, TreeNode>(Label.class);
    }

    public TreeNode get(Label key) {
        return labelled.get(key);
    }

    public Iterator<TreeNode> iterator() {
        return elements.iterator();
    }
}

class TreeNode1 extends TreeNode {
    TreeNode1(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.logical_and, elements.get(0));
    }
}

class TreeNode2 extends TreeNode {
    TreeNode2(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.or, elements.get(1));
        labelled.put(Label.logical_and, elements.get(3));
    }
}

class TreeNode3 extends TreeNode {
    TreeNode3(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.primary, elements.get(0));
    }
}

class TreeNode4 extends TreeNode {
    TreeNode4(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.and, elements.get(1));
        labelled.put(Label.primary, elements.get(3));
    }
}

class TreeNode5 extends TreeNode {
    TreeNode5(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.logical_or, elements.get(1));
    }
}

class TreeNode6 extends TreeNode {
    TreeNode6(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.decimal, elements.get(6));
    }
}

class TreeNode7 extends TreeNode {
    TreeNode7(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.app_version, elements.get(6));
    }
}

class TreeNode8 extends TreeNode {
    TreeNode8(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.app_version, elements.get(6));
    }
}

class TreeNode9 extends TreeNode {
    TreeNode9(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.app_version, elements.get(6));
    }
}

class TreeNode10 extends TreeNode {
    TreeNode10(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.app_version, elements.get(6));
    }
}

class TreeNode11 extends TreeNode {
    TreeNode11(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.decimal, elements.get(6));
    }
}

class TreeNode12 extends TreeNode {
    TreeNode12(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.decimal, elements.get(6));
    }
}

class TreeNode13 extends TreeNode {
    TreeNode13(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.decimal, elements.get(6));
    }
}

class TreeNode14 extends TreeNode {
    TreeNode14(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.decimal, elements.get(6));
    }
}

class TreeNode15 extends TreeNode {
    TreeNode15(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.decimal, elements.get(6));
    }
}

class TreeNode16 extends TreeNode {
    TreeNode16(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.decimal, elements.get(10));
    }
}

class TreeNode17 extends TreeNode {
    TreeNode17(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(6));
    }
}

class TreeNode18 extends TreeNode {
    TreeNode18(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.in, elements.get(4));
        labelled.put(Label.number_list, elements.get(7));
    }
}

class TreeNode19 extends TreeNode {
    TreeNode19(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.in, elements.get(4));
        labelled.put(Label.string_list, elements.get(7));
    }
}

class TreeNode20 extends TreeNode {
    TreeNode20(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.rev_all, elements.get(4));
        labelled.put(Label.number_list, elements.get(7));
    }
}

class TreeNode21 extends TreeNode {
    TreeNode21(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
        labelled.put(Label.rev_all, elements.get(4));
        labelled.put(Label.string_list, elements.get(7));
    }
}

class TreeNode22 extends TreeNode {
    TreeNode22(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.decimal, elements.get(1));
    }
}

class TreeNode23 extends TreeNode {
    TreeNode23(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.decimal, elements.get(2));
    }
}

class TreeNode24 extends TreeNode {
    TreeNode24(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(1));
    }
}

class TreeNode25 extends TreeNode {
    TreeNode25(String text, int offset, List<TreeNode> elements) {
        super(text, offset, elements);
        labelled.put(Label.alphanumeric, elements.get(2));
    }
}
