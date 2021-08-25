function ValidateDOB() {

    var lblError = document.getElementById("lblError");

    //Get the date from the TextBox.
    var dateString = document.getElementById("date").value;
    var regex = /(((0|1)[0-9]|2[0-9]|3[0-1])\/(0[1-9]|1[0-2])\/((19|20)\d\d))$/;

    //Check whether valid dd/MM/yyyy Date Format.
    if (regex.test(dateString)) {
        return true;
    } else {
        lblError.innerHTML = "Enter date in dd/MM/yyyy format ONLY."
        return false;
    }
};