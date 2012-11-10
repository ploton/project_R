var mongo = require('mongoose');
var dbUri = process.env.MONGOHQ_URL || 'mongodb://localhost/animeTest1'

mongo.connect(dbUri);//mongoDBのデータベース名を指定する

var Schema = mongo.Schema;

var Anime = mongo.model('animes', new Schema({
    title: String,
    outline: String,
    createAt: {type: Date, default: Date.now}
})
);
module.exports = Anime;
console.log('connected to MongoDB by Mongoose!');