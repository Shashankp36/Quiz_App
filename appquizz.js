

STORE = [
    { question: "What does the abbreviation HTML stand for?",
    answer: ["Hpyer Text Makeup Language", "High Text Markup Langauge", "Hyper Text Markup Language", "Hyper Text Markdown Language" ],
    correctAnswer:"Hyper Text Markup Language"
    },
    { question: " How many sizes of headers are available in HTML by default?",
    answer: ["1", "5", "4", "6"],
    correctAnswer:"6"
    },
    { question: "What What is the smallest header in HTML by default?",
    answer: ["h1", "h2", "h3", "h6"],
    correctAnswer: "h1"
    },
    { question: "How to display preformatted text in HTML?",
    answer: ["&lt;p&gt;", "&lt;pre&gt;", "&lt;a&gt;", "All of the above"],
    correctAnswer: "&lt;p&gt;"
    },
    { question: "Which of the following tags doesnt require a closing tag?",
    answer: ["br", "hr", "Both (a) and (b)", "none"],
    correctAnswer: "Both (a) and (b)"
    }
]
/* sets initial values to zero for the question number and score */

let qNumber = 0;
let score = 0;

/* event listener for start quiz button. Hides the start page and calls the function generateQuizQuestion */
function startQuiz() {
    $('main').on('click', '#button-start', function(event){
        $('.start-quiz').hide();
        generateQuizQuestion();
    });
}

/* begins displaying quiz questions from the STORE array until the very last question has been displayed, then calls the displayResults function */
function generateQuizQuestion() {
    if (qNumber < STORE.length) {
    let question =$(`<form class ="js-quiz-form">
    <legend class = "question">${STORE[qNumber].question}</legend>
    <ul class="radiogroup" role="radiogroup" aria-labelledby="question"></ul>`);
    let answers = STORE[qNumber].answer.map(function(answerValue, answerIndex){
        return `<label for="${answerValue}"><input type="radio" id="${answerValue}" name="answer" tabindex="${answerIndex}" value="${answerValue}" aria-checked="false" required>${answerValue}</label><br>`;
    });
    let button = $(`<button type="submit" id ="button-submit">Submit</button></form>`)
    $('.js-quiz').append(question);
    $('.radiogroup').append(answers, button);
    questionNumber();
} else {
    displayResults();
}

}

/* event listener for the submit button. Then checks to see if an input is selected, and if the answer selected is correct */
function questionChecker(){
    $('main').on('click','#button-submit', function (event){
        if ($('input:radio').is(':checked')) {
        event.preventDefault();
        let selectedAnswer= $("input[name=answer]:checked").val();
        console.log(selectedAnswer);
        if (selectedAnswer === STORE[qNumber].correctAnswer) {
            rightAnswer();
        } else {
                wrongAnswer();
            }
        }else {
            alert('Please select an answer.')
        }
    });
}

/* updates the question number and displays it at the top of the page */
function questionNumber(){
    $('header').find('#question-number').text(qNumber+1);
}

/* keeps score of correct answers and displays at the top of the page */
function scoreKeeper(){
    score++;
    $('header').find('#score').text(`${score}/5`);

}

/* displays the page for when the answer is right, updates score accordingly */
function rightAnswer() {
    console.log('rightAnswer ran');
    $('.js-quiz-form').hide();
    $('.js-answer').append(`<h2>You're Right!</h2>
    <p>Great Job.</p>
    <button type="button" id ="button-next">Next Question</button>`).show();
    scoreKeeper();
}

/* displays page for when the answer is wrong and displays the correct answer */
function wrongAnswer() {
    $('.js-quiz-form').hide();
    $('.js-answer').append(`<h2>That answer is not quite right...</h2>
        
        <h3>The correct answer is:</h3>
        <p><span class="correct-answer">${STORE[qNumber].correctAnswer}</span></p>
        <button type="button" id ="button-next">Next</button>`).show();
}

/* event listener for the next question button, calls the generateQuizQuestion function to display the next question */
function nextQuestion() {
    $('main').on('click','#button-next', function(event) {
        $('.js-answer').empty();
        $('.js-quiz-form').empty();
        qNumber++;
        generateQuizQuestion();
        $('js-quiz-form').show();
    });
}

/* displays the final percentage score and total number of correct answers */
function displayResults(){
    console.log("`displayResults` ran");
    let finalScore = (score/5)*100;
    $('.js-answer').append(`<h2>Quiz Results</h2>
    <h3>${finalScore}%</h3>
    <p>You got <span class="right-answers">${score} </span>out of 5 questions right.</p>
    <button type="button" id ="button-restart">Start a New Quiz</button>`)
}

function restartQuiz(){
    console.log('restart quiz ran');
 $('main').on('click', '#button-restart', function(event){
     console.log('restart button clicked');
    score = 0;
    qNumber = 0;
    $('.js-answer').empty();
    $('.js-quiz-form').empty();
    $('.start-quiz').show();
    $('header').find('#score').text(`${score}/5`);
    $('header').find('#question-number').text(`${qNumber}`);
 });
}

function handleQuizApp(){
    startQuiz();
    questionChecker();
    nextQuestion();
    restartQuiz();
}

/* calls the handleQuizApp to activate functions with event listeners */
$(handleQuizApp);