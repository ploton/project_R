
/*
 * GET home page.
 */
var anime = require('../models/anime');
/*
exports.index = function(req, res){
  res.render('index', { title: 'Express' });
};
*/
exports.index = function(req, res) {
    anime.find({}, function(err, animes) {
        if(err){
            throw err;
        }
        console.log("this is"+animes);
        res.render('index', {title:'Anime', animes:animes});//ここでオブジェクトを渡すことでindex.jadeからanimesが使えるようになる

    });
};
