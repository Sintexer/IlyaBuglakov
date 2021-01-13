async function sendLocale(localeName) {
    let response = await fetch("/rest/changeLocale?userLocale=" + localeName).then(() => location.reload());
    if(!response.ok){
        alert("An error occurred") ;
        return;
    }
    // location.reload();
}