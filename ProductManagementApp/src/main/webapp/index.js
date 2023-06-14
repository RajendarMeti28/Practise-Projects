

const addProduct = () => {
	const workArea = document.getElementById("workArea");
	let html="<table align='center' border=1>";
	html+="<tr><td>Product Code:</td><td><input id='product_code' /></tr>";
	html+="<tr><td>Product Name:</td><td><input id='product_name' /></tr>";
	html+="<tr><td>Product Price:</td><td><input id='product_price' /></tr>";
	html+="<tr><td>Product Rating:</td><td><input id='product_rating'/></tr></table><br/><br/>";
	workArea.innerHTML = html;
	const button = document.createElement("button");
	button.innerHTML='ADD';
	button.onclick = addProductToDb;
	workArea.appendChild(button);
	
}

const addProductToDb = () => {
	
	const pCodeE = document.getElementById("product_code");
	const pCode = pCodeE?pCodeE.value:'';
	const pNameE = document.getElementById("product_name");
	const pName = pNameE?pNameE.value:'';
	const pPriceE = document.getElementById("product_price");
	const pPrice = pPriceE?pPriceE.value:'';
	const pRatingE = document.getElementById("product_rating");
	const pRating = pRatingE?pRatingE.value:'';
	const product = {
		productCode: pCode,
		productName: pName,
		productPrice: pPrice,
		productRating: pRating
	}
	const productJson = JSON.stringify(product);
	console.log(productJson);
	postServerRequestAdd(productJson);
}

const postServerRequestAdd = (jsonStr) => {
    fetch('addProduct',{
        method:'POST',
        headers:{
            'Accept':'application/json, text/plain, */*',
            'Content-type':'application/json'},
        body: jsonStr
    })
    .then((res) =>res.json())
    .then((data) => {
		console.log("data:  ");
        if(data.result === "success"){
			recordAddedSuccessFully();
		}else{
			recordAddingFailed();
		}
    })
    .catch((err) => console.error(err));
}

const recordAddedSuccessFully = () =>{
	const workArea = document.getElementById("workArea");
	let html = "<h2>Record Added Successfully</h2>";
	workArea.innerHTML = html;
}

const recordAddingFailed = () => {
	const workArea = document.getElementById("workArea");
	let html = "<h2>Something went wrong.. Record Adding Failed.</h2>";
	workArea.innerHTML = html;
}

const searchProduct = () => {
	const workArea = document.getElementById("workArea");
	let html="Enter Product code to Search: <input id='product_code'/><br/><br/>";
	workArea.innerHTML = html;
	const button = document.createElement("button");
	button.innerHTML='SEARCH';
	button.onclick = searchProductFromDb;
	workArea.appendChild(button);
	
	
}

const searchProductFromDb = () => {
	const pCodeE = document.getElementById("product_code");
	const pCode = pCodeE?pCodeE.value:'';
	const product = {
		productCode: pCode,
	}
	const productJson = JSON.stringify(product);
	console.log(productJson);
	postServerRequestSearch(productJson);
	
}

function postServerRequestSearch(jsonStr){
    fetch('searchProduct',{
        method:'POST',
        headers:{
            'Accept':'application/json, text/plain, */*',
            'Content-type':'application/json'},
        body: jsonStr
    })
    .then((res) =>res.json())
    .then((data) => {
        console.log(data);//for now, just printing the data
         if(data.product!=""){
			showSearchResults(data.product);
		}else{
			noRecordsFound();
		}
    })
    .catch((err) => console.error(err));
}


const showSearchResults = (productDetails) => {
	const workArea = document.getElementById("workArea");
	let html = "<h3>Product details:</h3><br/>";
	html += `<table align='center' border=1>`;
	html+=`<tr><td>Product Code:</td><td>${productDetails.productCode}</td></tr>`;
	html+=`<tr><td>Product Name:</td><td>${productDetails.productName}</td></tr>`;
	html+=`<tr><td>Product Price:</td><td>${productDetails.productPrice}</td></tr>`;
	html+=`<tr><td>Product Rating:</td><td>${productDetails.productRating.toFixed(2)}</td></tr></table><br/><br/>`;
	
	workArea.innerHTML = html;
	
}

const noRecordsFound = () => {
	const workArea = document.getElementById("workArea");
	let html="No records found for the given code.";
	workArea.innerHTML = html;	
}


const updateProduct = () => {
	const workArea = document.getElementById("workArea");
	let html="Enter Product code to Update: <input id='product_code' /><br/></br/>";
	workArea.innerHTML = html;
	const button = document.createElement("button");
	button.innerHTML='Update';
	button.onclick = searchProductForUpdate;
	workArea.appendChild(button);
}


const searchProductForUpdate = () => {
	const pCodeE = document.getElementById("product_code");
	const pCode = pCodeE?pCodeE.value:'';
	const product = {
		productCode: pCode,
		call:"1",
	}
	const productJson = JSON.stringify(product);
	console.log(productJson);
	postServerRequestUpdate(productJson);
	
}

const postServerRequestUpdate = (json) => {
	fetch('updateProduct',{
        method:'POST',
        headers:{
            'Accept':'application/json, text/plain, */*',
            'Content-type':'application/json'},
        body: json
    })
    .then((res) =>res.json())
    .then((data) => {
        console.log(data);//for now, just printing the data
        if(data.result){
				if(data.result=='success')
					updatedSuccesfully();
				else
					updateFailed();
			}
         else if(data.product!=""){
			showUpdateTable(data.product);
		}else{
			noRecordsFound();
		}
    })
    .catch((err) => console.error(err));
}

const showUpdateTable = (product) => {
	console.log(product);
	const workArea = document.getElementById("workArea");
	let html="<table align='center' border=1>";
	html+=`<tr><td>Product Code:</td><td><input id='product_code' disabled=true value=${product.productCode} /></td></tr>`;
	html+=`<tr><td>Product Name:</td><td><input id='product_name' value=${product.productName} /></td></tr>`;
	html+=`<tr><td>Product Price:</td><td><input id='product_price' value=${product.productPrice} /></td></tr>`;
	html+=`<tr><td>Product Rating:</td><td><input id='product_rating' value=${product.productRating.toFixed(2)} /></td></tr></table><br/><br/>`;
	workArea.innerHTML = html;
	const button = document.createElement("button");
	button.innerHTML='UPDATE';
	button.onclick = updateProductToDb;
	workArea.appendChild(button);
}

const updateProductToDb = () => {
	const pCodeE = document.getElementById("product_code");
	const pCode = pCodeE?pCodeE.value:'';
	const pNameE = document.getElementById("product_name");
	const pName = pNameE?pNameE.value:'';
	const pPriceE = document.getElementById("product_price");
	const pPrice = pPriceE?pPriceE.value:'';
	const pRatingE = document.getElementById("product_rating");
	const pRating = pRatingE?pRatingE.value:'';
	const product = {
		productCode: pCode,
		productName: pName,
		productPrice: pPrice,
		productRating: pRating,
		call:"2"
	}
	const productJson = JSON.stringify(product);
	console.log(productJson);
	postServerRequestUpdate(productJson);
}

const updatedSuccesfully = () => {
	const workArea = document.getElementById("workArea");
	let html = "<h2>Record Updated Successfully</h2>";
	workArea.innerHTML = html;
}

const updateFailed = () => {
	const workArea = document.getElementById("workArea");
	let html = "<h2>Something went wrong. Record Updating failed..</h2>";
	workArea.innerHTML = html;
}



const deleteProduct = () => {
	const workArea = document.getElementById("workArea");
	let html="Enter Product code to Delete: <input id='product_code'/><br/><br/>";
	workArea.innerHTML = html;
	const button = document.createElement("button");
	button.innerHTML='DELETE';
	button.onclick = deleteProductFromDb;
	workArea.appendChild(button);
}


const deleteProductFromDb = () => {
	const pCodeE = document.getElementById("product_code");
	const pCode = pCodeE?pCodeE.value:'';
	const product = {
		productCode: pCode,
	}
	const productJson = JSON.stringify(product);
	console.log(productJson);
	postServerRequestDelete(productJson);
}


function postServerRequestDelete(jsonStr){
    fetch('deleteProduct',{
        method:'POST',
        headers:{
            'Accept':'application/json, text/plain, */*',
            'Content-type':'application/json'},
        body: jsonStr
    })
    .then((res) =>res.json())
    .then((data) => {
        if(data.result == "success"){
			recordDeletedSuccessFully();
		}else{
			recordDeletingFailed();
		}
    })
    .catch((err) => console.error(err));
}

const recordDeletedSuccessFully = () => {
	const workArea = document.getElementById("workArea");
	let html = "<h2>Record Deleted Successfully</h2>";
	workArea.innerHTML = html;
}

const recordDeletingFailed = () => {
	const workArea = document.getElementById("workArea");
	let html = "<h2>Something went wrong.. Record Deleting Failed.</h2>";
	workArea.innerHTML = html;
}

