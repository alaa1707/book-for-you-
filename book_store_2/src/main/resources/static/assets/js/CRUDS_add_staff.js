
// ################################################################################################################################
// ################################################################################################################################
// ########################################               ADD_STAFF            ####################################################
// ################################################################################################################################
// ################################################################################################################################



// get data from page html
let staff_name = document.getElementById("Name");
let staff_email = document.getElementById("Email");
let staff_phoneNumber = document.getElementById("PhoneNumber");
let submit_add_staff = document.getElementById("submit_add_staff");



// ###########################################################################
const elementSuccess = document.getElementById("messageSuccess");
const elementError = document.getElementById("messageError");
const elementUpdate = document.getElementById("messageUpdate");
// ###########################################################################


// local storage
let data_staff;
if (localStorage.staff != null) {
	data_staff = JSON.parse(localStorage.staff);
} else {
	// initialize empty array
	data_staff = [];
}

// create product
submit_add_staff.onclick = function () 
{
	// make list with key and value to store it in local storage
	let new_staff = {
		name: staff_name.value.toLowerCase(),
		email: staff_email.value.toLowerCase(),
		phoneNumber: staff_phoneNumber.value.toLowerCase(),
	};

	// check if the user send empty values to store it or no
	if (staff_name.value != "" && staff_email.value != "" && staff_phoneNumber.value != "")
		{
		// if (mood === "create") {
			data_staff.push(new_staff);
			// display message success
			elementSuccess.style.display = "block";
			setTimeout(() => {
				elementSuccess.style.display = "none";
			}, 2000);
			// clear cells
			staff_name.value = "";
			staff_email.value = "";
			staff_phoneNumber.value = "";
		} 
		else 
		{
		// display message error
			elementError.style.display = "block";
			setTimeout(() => {
				elementError.style.display = "none";
			}, 3000);
		}
	// save to local storage
	localStorage.setItem("staff", JSON.stringify(data_staff));
	console.log(localStorage.getItem('staff'));
    console.log(localStorage.staff);
};


