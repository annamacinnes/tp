package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MultipleDeleteCommand;
import seedu.address.logic.commands.RangeDeleteCommand;
import seedu.address.logic.commands.SingleDeleteCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new SingleDeleteCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, SingleDeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsMultipleIndices_returnsDeleteCommand() {
        assertParseSuccess(parser, "1,2,3",
                new MultipleDeleteCommand(new Index[]{ INDEX_FIRST_PERSON,
                        Index.fromOneBased(2), Index.fromOneBased(3) }));
    }

    @Test
    public void parse_invalidArgsMultipleIndices_throwsParseException() {
        assertParseFailure(parser, "1,a,3", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, MultipleDeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgsRangeIndices_returnsDeleteCommand() {
        assertParseSuccess(parser, "1-3",
                new RangeDeleteCommand(INDEX_FIRST_PERSON, Index.fromOneBased(3)));
    }

    @Test
    public void parse_invalidArgsRangeIndices_throwsParseException() {
        assertParseFailure(parser, "1-a", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, RangeDeleteCommand.MESSAGE_USAGE));
    }
}
