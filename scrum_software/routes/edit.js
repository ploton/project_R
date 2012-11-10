/**
 * Created with JetBrains WebStorm.
 * User: yoshiyuki
 * Date: 2012/10/20
 * Time: 0:59
 * To change this template use File | Settings | File Templates.
 */
var app= module.parent.exports //appオブジェクトにアクセスしている
exports.index = function(req, res){
    res.render('edit');
};
