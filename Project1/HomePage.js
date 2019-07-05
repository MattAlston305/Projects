window.onload = function(){
showUser();
}

function showUser()
{
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if(xhr.readyState == 4 && xhr.status == 200)
        {
            let obj = JSON.parse(xhr.responseText);
            showingUser(obj);
        }
    }
    xhr.open("GET", "BenCoordHomePage", true);
    xhr.send();
}

function showingUser(obj){
    let table = document.getElementById("trst");
    console.log(obj);
        let trow = table.insertRow();
        let cell1 = trow.insertCell().innerText = obj.userID;
        let cell2 = trow.insertCell().innerText = obj.username;
        let cell3 = trow.insertCell().innerText = obj.position;
        let cell4 = trow.insertCell().innerText = obj.availablebalance;
 
}