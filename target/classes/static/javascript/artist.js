/** farm.hmtlで使用するjavascriptを定義 
 * 
 */
 
 /*選択済みかどうかによって、値を変更する*/
 function checked(id){
	/*idに対応する要素の値が「選択」の場合*/
	if(document.getElementById(id).innerHTML == "選択"){
		document.getElementById(id).innerHTML = "選択済み"
	/*その他(「選択済」の場合)*/
	}else{
		document.getElementById(id).innerHTML = "選択"
	}
}

 /*選択した野菜が未選択の場合、カートにいれ、選択済の場合、カートから削除*/
 function addVegi(farm, ...vegiInfos){
	/*野菜情報の一つ目(id)に対応する要素の値が「選択」の場合*/
	if(document.getElementById(vegiInfos[0]).innerHTML == "選択"){
		let thread = vegiInfos;
    	let table = document.getElementById("checkedVegiList");
		/*エレメントの生成(tr)*/
		let tr = document.createElement("tr");
		/*エレメントの生成(td)*/  
    	let farmTd = document.createElement("td");
    	farmTd.innerText = farm;
    	/*農家名をカラムに設定*/
    	tr.appendChild(farmTd);
    	/*引数の野菜情報をカラムに設定*/
    	thread.forEach((item) => {
        	let td = document.createElement("td");
        	td.innerText = item;
        	tr.appendChild(td);
      	});
    /*行をテーブルに追加*/
    table.children[0].appendChild(tr);
	
	/*その他(「選択済」の場合)*/
    }else{
		let table = document.getElementById("checkedVegiList");
		/*1つ目のtbodyを取り出す*/
		let tbody = table.children[0];
		/*idが一致する行を削除する*/
		for(let i = 0; i < tbody.children.length ; i++){
			let tr = tbody.children[i];
			if(tr.children[1].innerText == vegiInfos[0]){
				tr.remove();
			}
		}
  	}
  }
  
  /*カート内の合計金額を出す*/
  function sum(){
	let table = document.getElementById("checkedVegiList");
	let tbody = table.children[0];
	let p = document.getElementById("sum");
	/*カートに野菜がない場合は、その旨の表示*/
	if(tbody.children.length == 1){
		p.innerHTML = "お野菜は選択されていません。";
	/*その他(カートに野菜がある場合)、合計金額を計算し設定*/
	}else{
		let sum = 0;
		for(let i = 1; i<tbody.children.length; i++){
			sum += parseInt(tbody.children[i].children[4].innerText);
		}
		p.innerHTML = "合計金額: " + sum;
	}
}

      