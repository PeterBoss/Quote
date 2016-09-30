$(document).ready(function () {
    
    ////////////////////////////////////////////////////////////////////////////
    //1.1.13
    ////////////////////////////////////////////////////////////////////////////
    
    function getRandomQuote() {
        $.ajax({
            url: "http://localhost:8080/RESTful1/api/quote/random"
        }).then(function (data) {
            $("#quoteDiv").text(data.quote);
        });
    }
    
    getRandomQuote();
    
    ////////////////////////////////////////////////////////////////////////////
    //1.1.14
    ////////////////////////////////////////////////////////////////////////////
    
    $("#RandomButton").click(getRandomQuote);
    
    ////////////////////////////////////////////////////////////////////////////
    //1.1.15
    ////////////////////////////////////////////////////////////////////////////
    
    
    
});