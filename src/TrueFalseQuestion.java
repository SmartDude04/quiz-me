/**
 * A TrueFalseQuestion has either a true or false answer.
 */
public class TrueFalseQuestion extends Question
{
    /**
     * Constructs a TrueFalseQuestion with the given prompt and answer.
     * @param prompt the question's prompt
     * @param answer the question's answer
     */
    public TrueFalseQuestion(String prompt, boolean answer)
    {
        super(prompt, String.valueOf(answer));
    }

    /**
     * Checks if the user's answer matches this TrueFalseQuestion's expected answer. This method will consider any of the following values (case-insensitive) to be true: T, True, Y, Yes. All other values will be considered false.
     * @param answer the user's answer
     * @return true if the user's answer is correct, false otherwise
     */
    @Override
    public boolean checkAnswer(String answer)
    {
        boolean userAnswer = false;
        boolean correctAnswer = super.getAnswer().equals("true");

        if (answer.equalsIgnoreCase("T"))
        {
            userAnswer = true;
        }
        else if (answer.equalsIgnoreCase("True"))
        {
            userAnswer = true;
        }
        else if (answer.equalsIgnoreCase("Y"))
        {
            userAnswer = true;
        }
        else if (answer.equalsIgnoreCase("Yes"))
        {
            userAnswer = true;
        }

        return userAnswer == correctAnswer;
    }

    /**
     * Returns the prompt for this Question prefixed with the text: "True or false: ".
     * @return the prompt for this Question prefixed with the text: "True or false: ".
     */
    @Override
    public String getPrompt()
    {
        return "True or False: " + super.getPrompt();
    }
}