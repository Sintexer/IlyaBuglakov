function createResponseObject(){
    let obj = {};
    obj["testId"] = document.getElementById("testId").value;
    obj["questions"] = createQuestionsList();

    console.log(obj);
    console.log(JSON.stringify(obj));
}

function createQuestionsList() {
    let questionsNode = document.getElementById("questions");
    let questions = questionsNode.querySelectorAll(".question");
    let questionObjects = [];

    for(let question of questions){
        questionObjects.push(createQuestionObject(question));
    }
    return questionObjects;
}

function createQuestionObject(question) {
    let obj = {};
    obj["id"] = question.querySelector(".questionId").value;
    obj["answers"] = [];
    for(let answer of question.querySelectorAll(".answer")){
        if(answer.querySelector(".answerOption").checked)
            obj["answers"].push(answer.querySelector(".answerId").value);
    }
    return obj;
}