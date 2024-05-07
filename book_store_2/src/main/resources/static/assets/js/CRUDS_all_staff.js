// ################################################################################################################################
// ################################################################################################################################
// ########################################               ALL_STAFF            ####################################################
// ################################################################################################################################
// ################################################################################################################################
let tmp;
let mood = "create";
let staff_name = document.getElementById("Name");
let staff_email = document.getElementById("Email");
let staff_phoneNumber = document.getElementById("PhoneNumber");
let submit_add_staff = document.getElementById("submit_add_staff");

const elementSuccess = document.getElementById("messageSuccess");
let edit_form = document.getElementById("form-edit");

var myObject = JSON.parse(localStorage.getItem("staff"));

// Read data form local storage and display it in table html
function showData() {
	let table = "";
	// loop through the array
	for (let i = 0; i < myObject.length; i++) {
		table += `
            <tr>
                <td>ST-${i + 1}</td>
                <td>${myObject[i].name}</td>
                <td>${myObject[i].email}</td>
                <td>${myObject[i].phoneNumber}</td>
                <td><button onclick="updateData(${i})" type="button" class="btn btn-rounded btn-outline-success">Edit</button></td>
                <td><button onclick="deleteData(${i})" type="button" class="btn btn-rounded btn-outline-danger"> Delete</button></td>
            </tr>
                `;
	}
	document.getElementById("tbody").innerHTML = table;
}
showData();



// delete
function deleteData(i) {
	myObject.splice(i, 1);
	localStorage.staff = JSON.stringify(myObject);
	showData();
}

let data_staff;
if (localStorage.staff != null) {
	data_staff = JSON.parse(localStorage.staff);
} else {
	// initialize empty array
	data_staff = [];
}

// update
function updateData(i) {
	edit_form.style.display = "block";
	staff_name.value = myObject[i].name;
	staff_email.value = myObject[i].email;
	staff_phoneNumber.value = myObject[i].phoneNumber;

	tmp = i;
	scroll({ top: 0, behavior: "smooth" });
}

submit_add_staff.onclick = function () {
	let staff_name = document.getElementById("Name");
	let staff_email = document.getElementById("Email");
	let staff_phoneNumber = document.getElementById("PhoneNumber");
	// make list with key and value to store it in local storage
	let new_staff = {
		name: staff_name.value.toLowerCase(),
		email: staff_email.value.toLowerCase(),
		phoneNumber: staff_phoneNumber.value.toLowerCase(),
	};
	data_staff[tmp] = new_staff;
	edit_form.style.display = "block";
	window.location.reload();
	localStorage.setItem("staff", JSON.stringify(data_staff));
	alert("Your staff data has been updated successfully");
	// showData();
};

// search
function searchData(value) {
	let table = "";
	for (let i = 0; i < myObject.length; i++) {
		if (
			myObject[i].role.includes(
				value.charAt(0).toUpperCase() + value.slice(1)
			)
		) {
			table += `
				<tr>
					<td>ST-${i + 1}</td>
					<td>${myObject[i].name}</td>
					<td>${myObject[i].email}</td>
					<td>${myObject[i].phoneNumber}</td>
					<td>
						<button
							onclick="updateData(${i})"
							type="button"
							class="btn btn-rounded btn-outline-success"
						>
							Edit
						</button>
					</td>
					<td>
						<button
							onclick="deleteData(${i})"
							type="button"
							class="btn btn-rounded btn-outline-danger"
						>
							Delete
						</button>
					</td>
				</tr>
			`;
		}
	}
	// show content in localStorage in table html
	document.getElementById("tbody").innerHTML = table;
}
