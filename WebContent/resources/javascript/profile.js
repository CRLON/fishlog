function showPasswordEdit() {
	var hiddenDiv = document.getElementById("editProfile");
    if (hiddenDiv.style.display === "none") {
    	hiddenDiv.style.display = "block";
    } else {
    	hiddenDiv.style.display = "none";
    }
}