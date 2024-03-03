import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The Quiz class allows the user to answer questions from a predefined question bank. The questions are displayed in a random order and a score is calculated at the end for how accurately the user answered the questions.
 */
public class Quiz
{
    /**
     * Question type for free response (i.e., "standard") questions.
     */
    public static final String FREE_RESPONSE_QUESTION_TYPE = "FR";
    /**
     * Question type for multiple choice questions.
     */
    public static final String 	MULTIPLE_CHOICE_QUESTION_TYPE = "MC";
    /**
     * Question type for true/false questions.
     */
    public static final String 	TRUE_FALSE_QUESTION_TYPE = "TF";

    private final String resourceName;

    /**
     * Constructs a Quiz with questions loaded from the given CSV file.
     * @param resourceName the name of the CSV file that holds the questions for this quiz, this file must be located in the same package as this class
     */
    public Quiz(String resourceName)
    {
        this.resourceName = resourceName;
    }

    /**
     * Creates a Quiz object from a local CSV resource named questions.csv and runs the quiz.
     * @param args not used by this method
     */
    public static void main(String[] args)
    {
        Quiz quiz = new Quiz("questions.csv");
        quiz.run();
    }

    /**
     * Runs the quiz by asking each question and checking whether the user's response is correct. The order of the questions will be shuffled every time the quiz is run.
     */
    public void run()
    {
        // Get the questions in a Question[]
        ArrayList<String[]> csv = fileHandler(resourceName);

        // Shuffle the order of questions
        // Done here because Collections.shuffle only works on ArrayLists
        Collections.shuffle(csv);

        Question[] questions = makeQuestions(csv);

        // Store number of correct answers
        int numCorrect = 0;

        // Initialize a scanner
        Scanner input = new Scanner(System.in);

        System.out.println("""
                =======================
                 "Quiz Me" Test Review\s
                =======================

                """);

        for (int i = 0; i < questions.length; i++)
        {
            Question question = questions[i];
            System.out.println("Question #" + (i + 1) + "\n------------");

            // Print the question itself
            System.out.println(question.getPrompt());

            // Get the user's answer
            String userAnswer = input.nextLine();

            // Check if the user was correct
            boolean correct = question.checkAnswer(userAnswer);

            if (correct)
            {
                System.out.println("Correct!\n");
                numCorrect++;
            }
            else
            {
                System.out.println("Sorry, the correct answer is " + question.getAnswer() + "\n");
            }
        }

        // Display the user's score at the end
        int percent = (int) (((double) numCorrect / questions.length) * 100);
        System.out.println("Your score: " + percent + "%");
    }

    /**
     * Opens csv files located within the package and parses through them
     * @param fileName The name of the file being opened
     * @return An ArrayList containing each line, the String[] is used to separate different values in the same line
     */
    private static ArrayList<String[]> fileHandler(String fileName)
    {
        ArrayList<String[]> file = new ArrayList<>();

        try
        {
            InputStream stream = Quiz.class.getClassLoader().getResourceAsStream(fileName);
            assert stream != null;
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            while (reader.ready())
            {
                String line = reader.readLine();
                String[] elements = line.split(",");

                file.add(elements);
            }

            reader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found: " + e.getMessage());
        }
        catch (IOException e)
        {
            System.out.println("Error reading line: " + e.getMessage());
        }

        return file;
    }

    /**
     * Parses through the csv in the form of an ArrayList and constructs objects that represent each question
     * @param csv The ArrayList of String[]'s that contain each question
     * @return The array storing each question in the form of the corresponding question object
     */
    private static Question[] makeQuestions(ArrayList<String[]> csv)
    {
        Question[] questions = new Question[csv.size()];

        // Loop through all questions in text format
        for (int i = 0; i < csv.size(); i++)
        {
            String[] question = csv.get(i);

            // Determine the type of the question
            switch (question[0])
            {
                // IntelliJ suggested I use a switch instead, so I did...
                // Maybe Cole is behind that to make my code "way more efficient"...

                case FREE_RESPONSE_QUESTION_TYPE ->
                {
                    // Construct a free response question
                    Question frq = new Question(question[1], question[2]);

                    // Add it to the questions ArrayList
                    questions[i] = frq;
                }
                case MULTIPLE_CHOICE_QUESTION_TYPE ->
                {
                    // Get the choices for the mcq in a String[]
                    String[] options = new String[question.length - 3];

                    System.arraycopy(question, 3, options, 0, question.length - 3);

                    // Construct a(n) mcq question and
                    MultipleChoiceQuestion mcq = new MultipleChoiceQuestion(question[1], question[2], options);

                    // Add it to the questions ArrayList
                    questions[i] = mcq;
                }
                case TRUE_FALSE_QUESTION_TYPE ->
                {
                    // Convert the string true/false to a boolean true/false
                    boolean answer = question[2].equalsIgnoreCase("true");

                    // Construct the true/false question
                    TrueFalseQuestion tfq = new TrueFalseQuestion(question[1], answer);

                    // Add it to the array
                    questions[i] = tfq;
                }
            }
        }

        // Return the now completed array
        return questions;
    }
}