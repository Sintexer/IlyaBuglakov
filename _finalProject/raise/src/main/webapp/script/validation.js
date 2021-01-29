

function validatePassword(msg) {
    let pass1 = document.getElementById("password");
    let pass2 = document.getElementById("passwordR");
    if(pass1.value!==pass2.value)
        pass2.setCustomValidity(msg);
    else
        pass2.setCustomValidity('');
}