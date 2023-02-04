package com.github.sidhant92.boolparser.parser.antlr;

import java.util.Optional;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import com.github.sidhant92.boolparser.domain.Node;
import com.github.sidhant92.boolparser.parser.BoolExpressionParser;

public class BooleanFilterParser implements BoolExpressionParser {
    @Override
    public Optional<Node> parseExpression(final String expression) {
        FilterLexer filterLexer = new FilterLexer(CharStreams.fromString(expression));
        CommonTokenStream commonTokenStream = new CommonTokenStream(filterLexer);
        FilterParser filterParser = new FilterParser(commonTokenStream);
        FilterParser.FilterContext filterContext = filterParser.filter();

        ParseTreeWalker parseTreeWalker = new ParseTreeWalker();
        final BooleanFilterListener listener = new BooleanFilterListener(filterLexer);
        parseTreeWalker.walk(listener, filterContext);
        return Optional.ofNullable(listener.getNode());
    }

    public static void main(String[] args) {
        final BooleanFilterParser booleanFilterParser = new BooleanFilterParser();
        final Optional<Node> a = booleanFilterParser.parseExpression("a > 25");
        System.out.println("");
    }
}
