
/**
 * Module dependencies.
 */

var express = require('express')
  , routes = require('./routes')//.indexしたいファイルはここでrequireしないとダメ
  , user = require('./routes/user')
  , http = require('http')
  , path = require('path')
  , edit = require('./routes/edit')
  ,anime = require('./models/anime')
  ;


//var app = express();
var app = module.exports = express(); //これで他のファイルから、module.parent.exportsでappにアクセスすることができるようになった

app.configure(function(){
  app.set('port', process.env.PORT || 3000);
  app.set('views', __dirname + '/views');
  app.set('view engine', 'jade');
  app.use(express.favicon());
  app.use(express.logger('dev'));
  app.use(express.bodyParser());
  app.use(express.cookieParser('secret','lorem ipsum'));
  app.use(express.session({
      key:'sess_id'
  }));
  app.use(express.methodOverride());
  app.use(app.router);
  app.use(express.static(path.join(__dirname, 'public')));
});

app.configure('development', function(){
  app.use(express.errorHandler());
});

app.locals({title:'express hands on!!'});//ヘルパを設定。どのファイルからでも値は参照できる


//app.get('/', routes.index);


//ルータを設定。後々別ファイル化するかも
app.get('/',function(req,res){
    anime.find({}, function(err, animes) {
        if(err){
            throw err;
        }
        res.render('index', {title:'Anime', animes:animes});
    });
});

app.get('/getAnimesData',function(req,res){//ajaxで全アニメの情報を返す
    anime.find({}, function(err, animes) {
        if(err){
            throw err;
        }
        res.send(animes);
    });
});

app.post('/testPost',function(req,res){//postのajaxの挙動確認
   console.log(req.body.test);//ajaxで渡したdataはreq.bodyに入る。json形式を指定して渡せば、プロパティにもアクセス可能。
   res.send("OK");
});

app.post('/addAnimeData',function(req,res){//ajaxで新しくアニメのデータを作る
    var ani = new anime();
    ani.title=req.param('title');
    ani.outline=req.param('outline');
    ani.save(function(err){
        if(err)throw err;
    });
});

app.get('/edit',edit.index);

app.get('/edit/:id',function(req,res){//ajaxでアニメの情報を編集するためにデータを返す
    anime.find({_id:req.param('id')},function(err,anime){
       if(err) throw err;
       res.send(anime);
    });
});
app.post('update/:id',function(req,res){
    anime.find({_id:req.param('id')},function(err,anime){
        if(err) throw err;
        anime.title = req.param('title');
        anime.outline  = req.param('outline');
        anime.save(function(err){
            if(err) throw err;
        });
    });
});



app.get('/foo',function(req,res){
    anime.find({}, function(err, animes) {
        if(err){
            throw err;
        }
        res.render('foo', {title:'Anime',animes:animes});
    });
});

app.get('/new',function(req,res){
    res.render('new',{title:'Anime(new)'});
});

app.post('/insert',function(req,res){
    var ani = new anime();
    console.log(req.param('title')+' ' +req.param('outline'));
    ani.title=req.param('title');
    ani.outline=req.param('outline');
    ani.save(function(err){
        if(err)throw err;
        res.redirect('/');
    });
});


app.get('/users', user.list);


//FATAL ERROR: JS Allocation failed - process out of memoryとかいうのが出た

http.createServer(app).listen(app.get('port'), function(){
  console.log("Express server listening on port " + app.get('port'));
});
