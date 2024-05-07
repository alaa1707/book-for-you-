// get data from page html
let prod_name = document.getElementById("slot1");
let prod_quantity = document.getElementById("slot2");
let prod_type = document.getElementById("slot3");
let submit = document.getElementById("submit");

// ############################
const elementSuccess = document.getElementById("messageSuccess");
const elementError = document.getElementById("messageError");
const elementUpdate = document.getElementById("messageUpdate");
// ############################

// $$$$$$$$$$$$$$$$$$$$$$$$$$$$
let mood = "create";
let tmp;
// $$$$$$$$$$$$$$$$$$$$$$$$$$$$

// local storage
let data_prod;
if (localStorage.product != null) {
	data_prod = JSON.parse(localStorage.product);
} else {
	// initialize empty array
	data_prod = [];
}

// create product
submit.onclick = function () {
	// make list with key and value to store it in local storage
	let new_prod = {
		name: prod_name.value.toLowerCase(),
		quantity: prod_quantity.value.toLowerCase(),
		type: prod_type.value,
	};

	// check if the user send empty values to store it or no
	if (prod_name.value != "" && prod_type.value != "" && prod_quantity != "") {
		if (mood === "create") {
			data_prod.push(new_prod);
			// display message success
			elementSuccess.style.display = "block";
			setTimeout(() => {
				elementSuccess.style.display = "none";
			}, 2000);
		} else {
			data_prod[tmp] = new_prod;
			mood = "create";
			submit.innerHTML = "Add product";
			// display message update
			elementUpdate.style.display = "block";
			setTimeout(() => {
				elementUpdate.style.display = "none";
			}, 2000);
		}
		clearData();
	} else {
		// display message error
		elementError.style.display = "block";
		setTimeout(() => {
			elementError.style.display = "none";
		}, 3000);
	}
	// save to local storage
	localStorage.setItem("product", JSON.stringify(data_prod));
	showData();
};

// clear inputs
function clearData() {
	prod_name.value = "";
	prod_quantity.value = "";
	prod_type.value = "";
}

// Read data form local storage and display it in table html
function showData() {
	let table = "";
	// loop through the array
	for (let i = 0; i < data_prod.length; i++) {
		table += `
            <tr>
                <td>${i + 1}</td>
                <td>${data_prod[i].name}</td>
                <td>${data_prod[i].quantity}</td>
                <td>${data_prod[i].type}</td>
                <td><button onclick="updateData(${i})" type="button" class="btn btn-rounded btn-outline-success">Edit</button></td>
                <td><button onclick="deleteData(${i})" type="button" class="btn btn-rounded btn-outline-danger"> Delete</button></td>
            </tr>
                `;
	}
	// display variable table in table html
	document.getElementById("tbody").innerHTML = table;
	// button to delete whole data
	let btnDelete = document.getElementById("deleteAll");
	if (data_prod.length > 0) {
		btnDelete.innerHTML = `<button onclick="deleteAll()" type="button" class="btn btn-danger btn-lg">Delete All(${data_prod.length})</button>`;
	} else {
		btnDelete.innerHTML = "";
	}
}

// to make table load without make refresh for the page
showData();

// delete from table one row
function deleteData(i) {
	data_prod.splice(i, 1);
	localStorage.product = JSON.stringify(data_prod);
	showData();
}

// delete whole rows
function deleteAll() {
	localStorage.clear();
	data_prod.splice(0);
	showData();
}

// update
function updateData(i) {
	prod_name.value = data_prod[i].name;
	prod_quantity.value = data_prod[i].quantity;
	prod_type.value = data_prod[i].type;
	submit.innerHTML = "Update";
	mood = "Update";
	tmp = i;

	scroll({ top: 0, behavior: "smooth" });
}

// search
let searchMood = "name";

function getSearchMood(id) {
	if (id == "searchName") {
		searchMood = "name";
	} else {
		// if i want to search with any thing except name i should change searchMood
		searchMood = "name";
	}
}

// Dynamic search
function searchData(value) {
	let table = "";
	for (let i = 0; i < data_prod.length; i++) {
		if (data_prod[i].name.includes(value.toLowerCase())) {
			table += `
                    <tr>
                        <td>${i + 1}</td>
                        <td>${data_prod[i].name}</td>
                        <td>${data_prod[i].quantity}</td>
                        <td>${data_prod[i].type}</td>
                        <td><button onclick="updateData(${i})" type="button" class="btn btn-rounded btn-outline-success">Edit</button></td>
                        <td><button onclick="deleteData(${i})" type="button" class="btn btn-rounded btn-outline-danger"> Delete</button></td>
                    </tr>
                `;
		}
	}
	// show content in localStorage in table html
	document.getElementById("tbody").innerHTML = table;
}

function selectOptions() {
	var list = ["Kitchen supplies", "Furniture", "Cleaning Products"];
	var option = "";
	for (i = 0; i < list.length; i++) {
		option += '<option value = "' + list[i] + '">' + list[i] + "</option>";
	}
	document.getElementById("slot3").innerHTML = option;
}
selectOptions();

function delete_offer(delete_id) {
	if (delete_id == "delete1") {
		const card1 = document.getElementById("delete1");
		card1.remove();
	} else if (delete_id == "delete2") {
		const card2 = document.getElementById("delete2");
		card2.remove();
	} else if (delete_id == "delete3") {
		const card3 = document.getElementById("delete3");
		card3.remove();
	} else if (delete_id == "delete4") {
		const card4 = document.getElementById("delete4");
		card4.remove();
	} else {
		alert("ERROR 404");
	}
}
// localStorage.removeItem("product");
