package by.geller.textproject.parser;

import by.geller.textproject.entity.impl.TextComposite;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractTextParser {
    protected AbstractTextParser nextParser = DefaultParser.getParser();

    public void setNextParser(AbstractTextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract TextComposite parse(String data);

    private static class DefaultParser extends AbstractTextParser {
        private static final Logger logger = LogManager.getLogger();
        private static final DefaultParser parser = new DefaultParser();

        public static DefaultParser getParser() {
            return parser;
        }

        @Override
        public TextComposite parse(String data) {
            logger.info("End of parser chain");
            return null;
        }
    }
}
