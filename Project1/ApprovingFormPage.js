window.onload = function(){
    showUser();
    }
    
    function showUser()
    {
        let xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if(xhr.readyState == 4 && xhr.status == 200)
            {
                console.log(xhr.responseText);
                let obj = JSON.parse(xhr.responseText);
                showingUser(obj);
            }
        }
        xhr.open("GET", "ApprovingFormPage", true);
        xhr.send();
    }
    function showingUser(obj){
        let table = document.getElementById("trst");
        console.log(obj);
        for(let i = 0; i<obj.length; i++)
        {
            let trow = table.insertRow();
            let cell1 = trow.insertCell().innerText = obj[i].formID;
            let cell = trow.insertCell().innerText = obj[i].eid;
            let cell2 = trow.insertCell().innerText = obj[i].eventName;
            let cell3 = trow.insertCell().innerText = obj[i].eventTime;
            let cell4 = trow.insertCell().innerText = obj[i].eventType;
            let cell5 = trow.insertCell().innerText = obj[i].status;

        }
            
     
    } 