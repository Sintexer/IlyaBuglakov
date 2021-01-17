const MIN_ANSWERS = 2;
const MAX_ANSWERS = 6;
const MIN_QUESTIONS = 1;
const MAX_QUESTIONS = 3;
const ANSWER_ROWS = 4;
const QUESTION_ROWS = 4;
const QUESTION_NAME_ROWS = 1;

let locales = {};

async function sendResult() {
    let testObj = createTestObj();
    if (validateTest(testObj)) {
        try {
            const response = await fetch("/rest/test/save", {
                method: 'POST',
                body: JSON.stringify(testObj),
                headers: {
                    'Content-Type': 'application/json; charset=utf-8'
                },
                redirect: "follow"
            });
            location.assign(response.text());
            location.reload();
            // let questions = document.getElementById("questions");
            // while (questions.hasChildNodes())
            //     questions.removeChild(questions.firstChild);
            // alert("Test saved successfully");
        } catch (error) {
            alert("an error has occurred, while posting test to server");
        }
    }
}

function createJson() {
    let testObj = createTestObj();
    console.log(validateTest(testObj));
    if (!validateTest(testObj)) {
        document.getElementById("testGuide").setAttribute("class", "alert");
        document.getElementById("testGuide").removeAttribute("hidden");
    }

    let testJson = JSON.stringify(testObj);
    console.log(testJson);
    return testJson;
}

function validateTest(test) {
    for (let question of test["questions"]) {
        if (!validateQuestion(question)) {
            return false;
        }
    }
    return test["questions"].length > 0 && test["testName"] !== '' && test["characteristics"].length !== 0
}

function validateQuestion(question) {
    if (question["name"] === null || question["name"] ==='')
        return false;
    let hasCorrect = false;
    for (let answer of question["answers"]) {
        if (answer["content"] === '')
            return false;
        if (answer["correct"] === true)
            hasCorrect = true;
    }
    return hasCorrect;
}

function createTestObj() {
    let testName = document.getElementsByName("testName")[0].value;
    let characteristics = document.getElementsByName("characteristic");
    let checkedCharacteristics = [];
    for (let characteristic of characteristics) {
        if (characteristic.checked) {
            checkedCharacteristics.push(characteristic.value);
        }
    }
    let questions = document.getElementById("questions").querySelectorAll(".question");
    let questionJsons = [];
    for (let questionNode of questions)
        questionJsons.push(createQuestionObj(questionNode));

    let obj = {};
    obj["testName"] = testName;
    obj["characteristics"] = checkedCharacteristics;
    obj["questions"] = questionJsons;
    return obj;
}

function createQuestionObj(questionNode) {
    let answers = questionNode.querySelector(".answers").querySelectorAll(".answer");
    let answerJsons = [];
    for (let answer of answers)
        answerJsons.push(createAnswerObj(answer));
    let questionContent = questionNode.querySelector(".question-content").value;
    let questionName = questionNode.querySelector(".question-name").value;

    let obj = {};
    obj["name"] = questionName;
    obj["content"] = questionContent;
    obj["answers"] = answerJsons;
    return obj;
}

function createAnswerObj(answerNode) {
    let obj = {};
    obj["correct"] = answerNode.querySelector("input").checked;
    obj["content"] = answerNode.querySelector("textarea").value;
    return obj;
}

function disableElement(element) {
    element.setAttribute("disabled", "disabled");
}

function enableElement(element) {
    element.removeAttribute("disabled");
}

function deleteAnswer(button) {
    let div = button.parentNode;
    let answersNode = div.parentNode;
    if (div.children.length > MIN_ANSWERS)
        answersNode.removeChild(div);
    switchAnswerDeleteButtons(answersNode);
    switchAddAnswerButtons(answersNode.parentNode);
}

function deleteQuestion(button) {
    let question = button.parentNode.parentNode;
    let questions = document.getElementById("questions");
    if (questions.querySelectorAll(".question").length > MIN_QUESTIONS) {
        questions.removeChild(question.nextSibling);
        questions.removeChild(question);
    }
    switchQuestionDeleteButtons(locales["questionTitle"]);
    switchAddQuestionButton();
}

function switchAnswerDeleteButtons(answersNode) {
    let deleteButtons = answersNode.querySelectorAll(".dlt-answer-button");
    let switchAction;
    if (deleteButtons.length <= MIN_ANSWERS) {
        switchAction = disableElement;
    } else {
        switchAction = enableElement;
    }
    for (let deleteButton of deleteButtons) {
        switchAction(deleteButton);
    }
    regenerateAnswersNumeration(answersNode);
}

function switchAddAnswerButtons(question) {
    let answers = question.querySelectorAll(".answer");
    if (answers.length >= MAX_ANSWERS) {
        question.querySelector(".add-answer-button").setAttribute("disabled", "disabled");
    } else {
        question.querySelector(".add-answer-button").removeAttribute("disabled");
    }
}

function switchQuestionDeleteButtons(questionTitleText) {
    let questionsNode = document.getElementById("questions");
    let buttons = questionsNode.querySelectorAll(".delete-question-btn");
    let switchAction;
    if (buttons.length <= MIN_QUESTIONS) {
        switchAction = disableElement;
    } else {
        switchAction = enableElement;
    }
    for (let button of buttons) {
        switchAction(button);
    }
    regenerateQuestionNumeration(questionTitleText);
}

function switchAddQuestionButton() {
    let addQuestionButton = document.getElementById("addQuestionButton");
    let questions = document.getElementById("questions").querySelectorAll(".question");
    let switchAction;
    if (questions.length >= MAX_QUESTIONS) {
        switchAction = disableElement;
    } else {
        switchAction = enableElement;
    }
    switchAction(addQuestionButton);
}

function regenerateAnswersNumeration(answersNode) {
    let numbers = answersNode.querySelectorAll(".answerOrder");
    let i = 1;
    for (let number of numbers) {
        number.innerHTML = i++ + ". ";
    }
}

function regenerateQuestionNumeration(titleText) {
    let questionTitles = document.getElementById("questions").querySelectorAll(".questionTitle");
    let i = 1;
    for (let questionTitle of questionTitles) {
        questionTitle.innerText = titleText + " " + i++ + ":";
    }
}

function addQuestion(button, questionTitleText, addAnswerButtonName, correctCaptionText, deleteQuestionText, questionNamePlaceholder) {
    locales["questionTitle"] = questionTitleText;
    // locales["addAnswerButtonName"] = addAnswerButtonName;
    locales["correct"] = correctCaptionText;
    // locales["deleteQuestionText"] = deleteQuestionText;
    if (document.getElementById("questions").querySelectorAll('.question').length >= MAX_QUESTIONS)
        return;
    let question = document.createElement("div");
    question.setAttribute("class", "question items-gap-vertical margin-l-2rem");

    let questionContentDiv = document.createElement("div");
    questionContentDiv.setAttribute("class", "flex");
    let questionContent = document.createElement("textarea")
    questionContent.setAttribute("class", "question-content unresize flex-auto form-input");
    questionContent.setAttribute("required", "required");
    questionContent.setAttribute("name", "question-content");
    questionContent.setAttribute("rows", QUESTION_ROWS.toString());
    questionContentDiv.appendChild(questionContent);

    let questionHeader = createQuestionHeader(deleteQuestionText);

    let questionName = document.createElement("textarea");
    questionName.setAttribute("class", "question-name unresize flex-auto form-input w-auto");
    questionName.setAttribute("required", "required");
    questionName.setAttribute("name", "question-name");
    questionName.setAttribute("placeholder", questionNamePlaceholder)
    questionName.setAttribute("rows", QUESTION_NAME_ROWS.toString());

    let answers = document.createElement("div");
    answers.setAttribute("class", "answers items-gap-vertical-sm");

    let addAnswerButton = createAddAnswerButton(addAnswerButtonName);

    question.appendChild(questionHeader);
    question.appendChild(questionName);
    question.appendChild(questionContentDiv);
    question.appendChild(answers);
    question.appendChild(addAnswerButton);

    let breakLine = document.createElement("div");
    breakLine.setAttribute("class", "breakline");

    document.getElementById("questions").appendChild(question);
    document.getElementById("questions").appendChild(breakLine);

    for (let i = 0; i < MIN_ANSWERS; ++i)
        addAnswer(addAnswerButton, correctCaptionText);

    switchAddQuestionButton();
    switchQuestionDeleteButtons(questionTitleText);
}

function addAnswer(button) {
    let answer = document.createElement("div");
    answer.setAttribute("class", "answer dec-pancake items-gap-sm");
    answer.appendChild(createAnswerOrder());
    answer.appendChild(createAnswerTextArea());
    answer.appendChild(createAnswerCorrect(locales["correct"]));
    answer.appendChild(createDeleteAnswerButton());
    let answers = button.parentNode.querySelector(".answers");
    answers.appendChild(answer);
    regenerateAnswersNumeration(answers);
    switchAddAnswerButtons(button.parentNode);
    switchAnswerDeleteButtons(button.parentNode.querySelector(".answers"));
}

function createQuestionHeader(deleteQuestionText) {
    let questionTitle = document.createElement("h3");
    questionTitle.setAttribute("class", "questionTitle");
    let deleteQuestionButton = document.createElement("button");
    deleteQuestionButton.setAttribute("class", "delete-question-btn btn btn-red margin-left-auto");
    deleteQuestionButton.setAttribute("type", "button");
    deleteQuestionButton.setAttribute("onclick", "deleteQuestion(this, 'Question')");
    deleteQuestionButton.innerHTML = deleteQuestionText;
    let div = document.createElement("div");
    div.setAttribute("class", "question-header flex align-items-center");
    div.appendChild(questionTitle);
    div.appendChild(deleteQuestionButton);
    return div;
}

function createAddAnswerButton(addAnswerButtonName) {
    let addAnswerButton = document.createElement("button");
    addAnswerButton.setAttribute("class", "btn btn-yellow margin-auto add-answer-button");
    addAnswerButton.setAttribute("type", "button");
    addAnswerButton.setAttribute("onclick", "addAnswer(this, 'correct')")
    addAnswerButton.innerHTML = addAnswerButtonName;
    return addAnswerButton;
}

function createAnswerOrder() {
    let answerOrder = document.createElement("span")
    answerOrder.setAttribute("class", "answerOrder");
    return answerOrder;
}

function createAnswerTextArea() {
    let answerTextArea = document.createElement("textarea")
    answerTextArea.setAttribute("class", "unresize flex-auto form-input w-auto");
    answerTextArea.setAttribute("required", "required");
    answerTextArea.setAttribute("name", "answer-content");
    answerTextArea.setAttribute("rows", ANSWER_ROWS.toString());
    return answerTextArea;
}

function createAnswerCorrect(captionText) {
    let caption = document.createElement("span");
    caption.setAttribute("class", "margin-r-2rem")
    caption.innerHTML = "<input type=\"checkbox\" name=\"answer-correct\"/>" + captionText;
    return caption;
}

function createDeleteAnswerButton() {
    let button = document.createElement("button");
    button.setAttribute("type", "button");
    button.setAttribute("class", "dlt-answer-button btn-sm btn-red rounded-10 margin-left-auto");
    button.setAttribute("onclick", "deleteAnswer(this)");
    button.innerHTML = "&times";
    return button;
}