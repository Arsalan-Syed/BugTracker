

const Model = function () {

    let queryText = null;
    let observers = [];


    this.setQueryText = function(text){
        queryText = text;
        this.notifyObservers({"queryText":text});
    }

    this.getQueryText = function(){
        return queryText;
    }

    this.addObserver = function (observer) {
        observers.push(observer);
    };

    this.notifyObservers = function (param) {
        observers.forEach(o => o.update(param));
    }
}

export const modelInstance = new Model();