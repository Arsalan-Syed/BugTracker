

const Model = function () {

    let queryText =null;

    this.setQueryText = function(text){
        queryText = text;
    }

}

export const modelInstance = new Model();