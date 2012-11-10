/**
 * Created with JetBrains WebStorm.
 * User: yoshiyuki
 * Date: 2012/10/26
 * Time: 16:43
 * To change this template use File | Settings | File Templates.
 * -- This code written by Yoshiyuki Kato. --
 */
$(function(){

    //モデルの設定
    var Anime = Backbone.Model.extend({
        initialize:function(){

        }
    });

    var AnimeCol = Backbone.Collection.extend({
        model:Anime
    });

    var AppModel = Backbone.Model.extend({
        initialize:function(){
            this.animeCol = new AnimeCol();
        },
        initAnime:function(){//データベースからアニメの情報をとってきて、Animeモデルに格納するメソッド。MongoDBの設定が必要
                             //とりあえずbackbone × express × MongoDBの資料がないと分かんないけど、listにアクセスは出来るんじゃなかろうか
            var that = this;
            $.ajax({
                url:"/getAnimesData",
                dataType:"json",
                success:function(data){
                    jQuery.each(data,function(){
                        that.animeCol.add(new Anime({title:this.title,outline:this.outline}));
                    })
                }
            });

        }
    });

    //ビューの設定
    var AnimeView = Backbone.View.extend({
        //{model:new Anime({})}で渡してくる
    });

    var AppView = Backbone.View.extend({
       //{appModel:AppModel}で渡してくる
       initialize:function(){
            //コレクションイベントのバインド。initanimeもここで行う
           this.model.animeCol.bind('add',this.initAnimeView,this);
           this.model.initAnime();
       },
       render:function(){
           return this;
       },
       initAnimeView:function(anime){
           var animeView = new AnimeView({model:anime});
           $("#animeList").append("<li>"+animeView.model.get('title')+"</li>"+"<li>"+animeView.model.get('outline')+"</li>");
       }
    });


    //ルータの設定
    var AppRouter = Backbone.Router.extend({
        ready :false,
        routes:{
            home : "",
            postAnime : "!/postAnime",
            animeRanking:"!/animeRanking/:id",//ジャンル別アニメランキングへ（ジャンルへのルータを一つずつ作るか微妙なところ。保留）
            animeDetail : "!/animeDetail/:id" //アニメの詳細情報ページへ
        },
        initialize:function(){
            this.init();
        },
        init:function(){
            if(this.ready===false){
                this.appModel = new AppModel();
                this.appView = new AppView({model:this.appModel});
                this.ready = true;
            }
        },
        home:function(){
            this.init();
        },
        postAnime:function(){//ajaxでpostする練習
            console.log("post");
            $.ajax({
                type:"POST",
                url:"/testPost",
                dataType:"json",
                data:{'test':'testData'},
                success:function(msg){
                    alert(msg);
                }
            });
        }
    });

    var appRouter = new AppRouter();
    Backbone.history.start();


});