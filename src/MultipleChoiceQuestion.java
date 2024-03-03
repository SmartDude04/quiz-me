/**
 * A MultipleChoiceQuestion includes list of possible answers.
 */
public class MultipleChoiceQuestion extends Question
{
    private final String[] choices;

    /**
     * Constructs a MultipleChoiceQuestion with the given prompt, answer, and choices.
     * @param prompt the question's prompt
     * @param answer the question's answer; this value should be a letter whose position in the alphabet corresponds to the index of the correct answer in choices. For example, the answer B corresponds to choices[1]
     * @param choices the list of choices for this question; this array can be of any length
     */
    public MultipleChoiceQuestion(String prompt, String answer, String[] choices)
    {
        super(prompt, answer.toLowerCase());
        this.choices = choices;
    }

    /**
     * Checks if the user's answer matches this MultipleChoiceQuestion's expected answer. This method will consider either the letter of the choice to be correct OR the actual text of the response (both checks are case-insensitive).
     * @param answer the user's answer
     * @return true if the user's answer is correct, false otherwise
     */
    @Override
    public boolean checkAnswer(String answer)
    {
        int choiceNum = super.getAnswer().charAt(0) - 'a';
        String directAnswer = choices[choiceNum];

        // Check if the answer matches the multiple choice selection
        if (answer.length() == 1 && (answer.toLowerCase().charAt(0) - 'a') == choiceNum)
        {
            return true;
        }

        // Check if the answer matches the answer directly
        return answer.equalsIgnoreCase(directAnswer);
    }

    /**
     * Returns the prompt for this MultipleChoiceQuestion followed by the list of choices.
     * @return the prompt for this MultipleChoiceQuestion followed by the list of choices.
     */
    @Override
    public String getPrompt()
    {
        String question = super.getPrompt();
        StringBuilder choiceBuilder = new StringBuilder();

        for (int i = 0; i < choices.length; i++)
        {

            choiceBuilder.append("\n");
            char letter = (char) (i + 'A');
            String front = letter + ": ";

            choiceBuilder.append(front);

            choiceBuilder.append(choices[i]);
        }

        String choices = choiceBuilder.toString();

        return question + choices;
    }

    /**
     * Returns the answer of the multiple choice question in a nicer, upper case format
     * Not needed, but it makes the game look nicer and has no effect on the client's implementation of this code
     * @return the answer in upper case
     */
    @Override
    public String getAnswer()
    {
        return super.getAnswer().toUpperCase();
    }
}
