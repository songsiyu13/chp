/**
 * Created by 滩涂上的芦苇 on 2016/6/14.
 */
var dataDiv;
var dataTableBody;
var curElement;
var XMLHttpReq=false;
//创建对象
function creatXMLHttpRequest(){
    XMLHttpReq= new XMLHttpRequest();
}

function getDetial(elment) {
    dataTableBody=document.getElementById("dataBody");
    dataDiv=document.getElementById("popup");
    creatXMLHttpRequest();
    curElement=elment;
    var url="Tips.action?key="+escape(elment.id);
    XMLHttpReq.open("GET",url,true);
    XMLHttpReq.onreadystatechange = processResponse;
    XMLHttpReq.send(null);
}

function processResponse() {
    if(XMLHttpReq.readyState==4){
        if(XMLHttpReq.status==200){
            setData(XMLHttpReq.responseText);
        }
        else
        {
            window.alert("forbidden");
        }
    }
}

function setData(data) {
    clearData();
    setOffsets();
    var content = eval("("+data+")").content;
    var row=createRow(content);
    dataTableBody.appendChild(row);
}

function createRow(data) {
    var row,cell,txtNode;
    row=document.createElement("tr");
    cell=document.createElement("td");
    cell.setAttribute("bgcolor","#FFFAFA");
    cell.setAttribute("border",0);
    txtNode=document.createTextNode(data);
    cell.appendChild(txtNode);
    row.appendChild(cell);
    return row;
}

function setOffsets() {
    dataDiv.style.border="black 1px solid";
    var top=0;
    while(curElement){
        top+=curElement["offsetTop"];
        curElement=curElement.offsetParent;
    }
    dataDiv.style.left=50+"px";
    dataDiv.style.top=top+"px";
}

function clearData() {
    var ind=dataTableBody.childNodes.length;
    for(var i=ind-1;i>=0;i--){
        dataTableBody.removeChild(dataTableBody.childNodes[i]);
    }
    dataDiv.style.border="none";
}