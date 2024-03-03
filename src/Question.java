/**
 * Parent class for all types of questions that may be asked in a Quiz. This default implementation represents a free response (i.e., "standard") question.
 */
public class Question
{
    private final String prompt;
    private final String answer;

    /**
     * Constructs a Question with the given prompt and answer.
     * @param prompt the question's prompt
     * @param answer the question's answer
     */
    public Question(String prompt, String answer)
    {
        this.prompt = prompt;
        this.answer = answer;
    }

    /**
     * Checks if the user's answer matches this Question's expected answer, ignoring case.
     * @param answer the user's answer
     * @return true if the user's answer is correct, false otherwise
     */
    public boolean checkAnswer(String answer)
    {
        return this.answer.equalsIgnoreCase(answer);
    }

    /**
     * Returns the answer for this Question.
     * @return the user's answer
     */
    public String getAnswer()
    {
        return answer;
    }

    /**
     * Returns the prompt for this Question.
     * @return the user's prompt
     */
    public String getPrompt()
    {
        return prompt;
    }

    /**
     * Returns the String representation of this Question.
     * @return the String representation of this Question.
     */
    @Override
    public String toString()
    {
        return prompt + "\n" + answer;
    }
}
