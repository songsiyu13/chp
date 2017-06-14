(function(root, factory) {
    if(typeof define === "function"){
        define("SPARQLWrapper", factory);    // AMD || CMD
    }else{
        root.SPARQLWrapper = factory();    // <script>
    }
}(this, function(){
'use strict'

function SPARQLWrapper(endpoint){
    this.endpoint = endpoint;
    this.queryPart = "";
    this.type = "json";
}
SPARQLWrapper.prototype = {
    constructor: SPARQLWrapper,
    setQuery: function(query){
        this.queryPart = "query=" + encodeURI(query);
    },
    setType: function(type){
        this.type = type.toLowerCase();
    },
    query: function(type, callback){
        callback = callback === undefined ? type : this.setType(type) || callback;
        
        var xhr = new XMLHttpRequest();
        xhr.open('POST', this.endpoint, true);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        switch(this.type){
            case "json":
                type = "application/sparql-results+json";
                break;
            case "xml":
                type = "text/xml";
                break;
            case "html":
                type = "text/html";
                break;
            default:
                type = "application/sparql-results+json";
                break;
        }
        xhr.setRequestHeader("Accept", type);
        xhr.onreadystatechange = function(){
            if(xhr.readyState == 4){
                var sta = xhr.status;
                if(sta == 200 || sta == 304){
                    callback(xhr.responseText);
                }else{
                    console && console.error("Sparql query error: " + xhr.status + " " + xhr.responseText);
                }
        
                window.setTimeout(function(){
                    xhr.onreadystatechange= new Function();
                    xhr = null;
                },0);
            }
        };
        
        xhr.send(this.queryPart);
    }
};


return SPARQLWrapper;

}));

var $ = function(id){
        return document.getElementById(id);
    },
    sparql = new SPARQLWrapper("http://dbpedia.org/sparql"),
    results = [];

function getInfo(name){
    //name = name.replace(/\s/g, "_");
    var command = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"
        +"SELECT DISTINCT ?x1 WHERE {?x0 rdf:type foaf:Person. ?x0 rdfs:label \""+name+"\"@en. ?x0 rdfs:comment ?x1.FILTER (langMatches(lang(?x1), \"EN\")) }";
    sparql.setQuery(command);
    sparql.query(function(json){
        showInfo((eval("(" + json + ")")).results.bindings);
    });
}

function showInfo(results){
    var text = "";
    if(results.length !== 0){
        for(var i = 0; i < results.length; i++){
            text += "<p>" + results[i].x1.value + "</p><br /><br /><br />";
            $("result").innerHTML = text;
        }
    }else{
        $("result").innerHTML = "没有任何相关信息！";
    }

}