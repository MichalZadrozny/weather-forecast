const $searchBox = document.querySelector('.search-input input');
const $searchButton = document.querySelector('.search-btn');
const $searchInfo = document.querySelector('.search-info')
const $searchForm = document.querySelector('.search-box form');

function isNotEmpty(){

    if($searchBox.value == ''){
        console.log("Search button clicked");
        $searchInfo.innerText = "Please provide the city name";
    }else{
        $searchInfo.innerText = "";
        $searchForm.submit();
    }
}


$searchButton.addEventListener("click",isNotEmpty);
$searchBox.addEventListener("keypress",(event) => {
    if(event.keyCode === 13){
        event.preventDefault();
        isNotEmpty();
    }
});