function validateSignup(){
    var name=document.mysignform.name.value;
    var email =document.mysignform.email.value;
    var password =document.mysignform.password.value;
    if(name.length<6){
        document.getElementById("nameSpan").innerHTML="First Name length can't be less than of length 6";
    }
    else{
        document.getElementById("nameSpan").innerHTML="";
    }
    if(email.length<6){
        document.getElementById("emailSpan").innerHTML="Email length can't be less than of length 6";
    }
    else if(!(email.match(/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/))){
        document.getElementById("emailSpan").innerHTML="Email doesn't match the expression.";
    }else{
        document.getElementById("emailSpan").innerHTML="";
    }
    if(password.length<6){
        document.getElementById("passwordSpan").innerHTML="Password length can't be less than of length 6";
    }else{
        document.getElementById("passwordSpan").innerHTML="";
    }
}

/*const search=()=> {
    let query= $("#search-input").val();
    console.log(query);

    if(query==''){

    }
    else{
        console.log(query);
        let url= `http://localhost:8080/search/$(query)`;

        fetch(url)
        .then((response) => {
            return response.json();
        })
            .then((data) => {
                

                let text=`<div class='list-group'>`
                
                data.forEach((games) => {
                    text+= `<a href= '/user/$(games.gId)/games' class= 'list-group-item list-group-item-action'> ${games.gamename} </a>`
                    
                });
                text+=`</div>`;
                $(".search-result").html(text);
                $(".search-result").show();
            });

       
    }
};*/