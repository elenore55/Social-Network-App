// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  HomeController_7: controllers.HomeController,
  // @LINE:13
  Assets_6: controllers.Assets,
  // @LINE:16
  PostController_0: controllers.PostController,
  // @LINE:25
  UserController_4: controllers.UserController,
  // @LINE:33
  ReactionController_5: controllers.ReactionController,
  // @LINE:40
  LoginController_2: controllers.LoginController,
  // @LINE:43
  FriendshipRequestController_3: controllers.FriendshipRequestController,
  // @LINE:51
  CommentController_1: controllers.CommentController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    HomeController_7: controllers.HomeController,
    // @LINE:13
    Assets_6: controllers.Assets,
    // @LINE:16
    PostController_0: controllers.PostController,
    // @LINE:25
    UserController_4: controllers.UserController,
    // @LINE:33
    ReactionController_5: controllers.ReactionController,
    // @LINE:40
    LoginController_2: controllers.LoginController,
    // @LINE:43
    FriendshipRequestController_3: controllers.FriendshipRequestController,
    // @LINE:51
    CommentController_1: controllers.CommentController
  ) = this(errorHandler, HomeController_7, Assets_6, PostController_0, UserController_4, ReactionController_5, LoginController_2, FriendshipRequestController_3, CommentController_1, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_7, Assets_6, PostController_0, UserController_4, ReactionController_5, LoginController_2, FriendshipRequestController_3, CommentController_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """explore""", """controllers.HomeController.explore()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """tutorial""", """controllers.HomeController.tutorial()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """posts""", """controllers.PostController.create"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """posts/me""", """controllers.PostController.getMyPosts"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """posts/""" + "$" + """id<[^/]+>""", """controllers.PostController.getUsersPosts(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """posts""", """controllers.PostController.getFriendsPosts"""),
    ("""PATCH""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """posts""", """controllers.PostController.update"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """posts/""" + "$" + """id<[^/]+>""", """controllers.PostController.remove(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """users/friends""", """controllers.UserController.getUsersFriends"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """users/me/profile""", """controllers.UserController.getMyData"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """users/""" + "$" + """id<[^/]+>/profile""", """controllers.UserController.getOtherUserData(id:Long)"""),
    ("""PATCH""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """users/me/profile""", """controllers.UserController.updateUserData()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """users/search/""" + "$" + """term<[^/]+>""", """controllers.UserController.search(term:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reactions/like/""" + "$" + """postId<[^/]+>""", """controllers.ReactionController.like(postId:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reactions/dislike/""" + "$" + """postId<[^/]+>""", """controllers.ReactionController.dislike(postId:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """reactions/""" + "$" + """postId<[^/]+>""", """controllers.ReactionController.removeReaction(postId:Long)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """auth/login""", """controllers.LoginController.login()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """auth/register""", """controllers.LoginController.register()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """friends/requests/received""", """controllers.FriendshipRequestController.getReceivedRequests"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """friends/requests/sent""", """controllers.FriendshipRequestController.getSentRequests"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """friends/requests""", """controllers.FriendshipRequestController.create"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """friends/requests/""" + "$" + """id<[^/]+>/""" + "$" + """status<[^/]+>""", """controllers.FriendshipRequestController.changeStatus(id:Long, status:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """comments""", """controllers.CommentController.create"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """comments/""" + "$" + """postId<[^/]+>""", """controllers.CommentController.getComments(postId:Long)"""),
    ("""PATCH""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """comments""", """controllers.CommentController.update"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """comments/""" + "$" + """id<[^/]+>""", """controllers.CommentController.remove(id:Long)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_7.index(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_HomeController_explore1_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("explore")))
  )
  private[this] lazy val controllers_HomeController_explore1_invoker = createInvoker(
    HomeController_7.explore(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "explore",
      Nil,
      "GET",
      this.prefix + """explore""",
      """""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_HomeController_tutorial2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("tutorial")))
  )
  private[this] lazy val controllers_HomeController_tutorial2_invoker = createInvoker(
    HomeController_7.tutorial(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "tutorial",
      Nil,
      "GET",
      this.prefix + """tutorial""",
      """""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_Assets_versioned3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned3_invoker = createInvoker(
    Assets_6.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_PostController_create4_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("posts")))
  )
  private[this] lazy val controllers_PostController_create4_invoker = createInvoker(
    PostController_0.create,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PostController",
      "create",
      Nil,
      "POST",
      this.prefix + """posts""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:17
  private[this] lazy val controllers_PostController_getMyPosts5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("posts/me")))
  )
  private[this] lazy val controllers_PostController_getMyPosts5_invoker = createInvoker(
    PostController_0.getMyPosts,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PostController",
      "getMyPosts",
      Nil,
      "GET",
      this.prefix + """posts/me""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_PostController_getUsersPosts6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("posts/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_PostController_getUsersPosts6_invoker = createInvoker(
    PostController_0.getUsersPosts(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PostController",
      "getUsersPosts",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """posts/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_PostController_getFriendsPosts7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("posts")))
  )
  private[this] lazy val controllers_PostController_getFriendsPosts7_invoker = createInvoker(
    PostController_0.getFriendsPosts,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PostController",
      "getFriendsPosts",
      Nil,
      "GET",
      this.prefix + """posts""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private[this] lazy val controllers_PostController_update8_route = Route("PATCH",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("posts")))
  )
  private[this] lazy val controllers_PostController_update8_invoker = createInvoker(
    PostController_0.update,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PostController",
      "update",
      Nil,
      "PATCH",
      this.prefix + """posts""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:23
  private[this] lazy val controllers_PostController_remove9_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("posts/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_PostController_remove9_invoker = createInvoker(
    PostController_0.remove(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.PostController",
      "remove",
      Seq(classOf[Long]),
      "DELETE",
      this.prefix + """posts/""" + "$" + """id<[^/]+>""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:25
  private[this] lazy val controllers_UserController_getUsersFriends10_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("users/friends")))
  )
  private[this] lazy val controllers_UserController_getUsersFriends10_invoker = createInvoker(
    UserController_4.getUsersFriends,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "getUsersFriends",
      Nil,
      "GET",
      this.prefix + """users/friends""",
      """""",
      Seq()
    )
  )

  // @LINE:26
  private[this] lazy val controllers_UserController_getMyData11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("users/me/profile")))
  )
  private[this] lazy val controllers_UserController_getMyData11_invoker = createInvoker(
    UserController_4.getMyData,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "getMyData",
      Nil,
      "GET",
      this.prefix + """users/me/profile""",
      """""",
      Seq()
    )
  )

  // @LINE:27
  private[this] lazy val controllers_UserController_getOtherUserData12_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("users/"), DynamicPart("id", """[^/]+""",true), StaticPart("/profile")))
  )
  private[this] lazy val controllers_UserController_getOtherUserData12_invoker = createInvoker(
    UserController_4.getOtherUserData(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "getOtherUserData",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """users/""" + "$" + """id<[^/]+>/profile""",
      """""",
      Seq()
    )
  )

  // @LINE:29
  private[this] lazy val controllers_UserController_updateUserData13_route = Route("PATCH",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("users/me/profile")))
  )
  private[this] lazy val controllers_UserController_updateUserData13_invoker = createInvoker(
    UserController_4.updateUserData(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "updateUserData",
      Nil,
      "PATCH",
      this.prefix + """users/me/profile""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:30
  private[this] lazy val controllers_UserController_search14_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("users/search/"), DynamicPart("term", """[^/]+""",true)))
  )
  private[this] lazy val controllers_UserController_search14_invoker = createInvoker(
    UserController_4.search(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.UserController",
      "search",
      Seq(classOf[String]),
      "GET",
      this.prefix + """users/search/""" + "$" + """term<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:33
  private[this] lazy val controllers_ReactionController_like15_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reactions/like/"), DynamicPart("postId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ReactionController_like15_invoker = createInvoker(
    ReactionController_5.like(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ReactionController",
      "like",
      Seq(classOf[Long]),
      "POST",
      this.prefix + """reactions/like/""" + "$" + """postId<[^/]+>""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:35
  private[this] lazy val controllers_ReactionController_dislike16_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reactions/dislike/"), DynamicPart("postId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ReactionController_dislike16_invoker = createInvoker(
    ReactionController_5.dislike(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ReactionController",
      "dislike",
      Seq(classOf[Long]),
      "POST",
      this.prefix + """reactions/dislike/""" + "$" + """postId<[^/]+>""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:37
  private[this] lazy val controllers_ReactionController_removeReaction17_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("reactions/"), DynamicPart("postId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ReactionController_removeReaction17_invoker = createInvoker(
    ReactionController_5.removeReaction(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ReactionController",
      "removeReaction",
      Seq(classOf[Long]),
      "DELETE",
      this.prefix + """reactions/""" + "$" + """postId<[^/]+>""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:40
  private[this] lazy val controllers_LoginController_login18_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("auth/login")))
  )
  private[this] lazy val controllers_LoginController_login18_invoker = createInvoker(
    LoginController_2.login(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LoginController",
      "login",
      Nil,
      "POST",
      this.prefix + """auth/login""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:41
  private[this] lazy val controllers_LoginController_register19_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("auth/register")))
  )
  private[this] lazy val controllers_LoginController_register19_invoker = createInvoker(
    LoginController_2.register(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.LoginController",
      "register",
      Nil,
      "POST",
      this.prefix + """auth/register""",
      """""",
      Seq()
    )
  )

  // @LINE:43
  private[this] lazy val controllers_FriendshipRequestController_getReceivedRequests20_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("friends/requests/received")))
  )
  private[this] lazy val controllers_FriendshipRequestController_getReceivedRequests20_invoker = createInvoker(
    FriendshipRequestController_3.getReceivedRequests,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FriendshipRequestController",
      "getReceivedRequests",
      Nil,
      "GET",
      this.prefix + """friends/requests/received""",
      """""",
      Seq()
    )
  )

  // @LINE:44
  private[this] lazy val controllers_FriendshipRequestController_getSentRequests21_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("friends/requests/sent")))
  )
  private[this] lazy val controllers_FriendshipRequestController_getSentRequests21_invoker = createInvoker(
    FriendshipRequestController_3.getSentRequests,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FriendshipRequestController",
      "getSentRequests",
      Nil,
      "GET",
      this.prefix + """friends/requests/sent""",
      """""",
      Seq()
    )
  )

  // @LINE:46
  private[this] lazy val controllers_FriendshipRequestController_create22_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("friends/requests")))
  )
  private[this] lazy val controllers_FriendshipRequestController_create22_invoker = createInvoker(
    FriendshipRequestController_3.create,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FriendshipRequestController",
      "create",
      Nil,
      "POST",
      this.prefix + """friends/requests""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:48
  private[this] lazy val controllers_FriendshipRequestController_changeStatus23_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("friends/requests/"), DynamicPart("id", """[^/]+""",true), StaticPart("/"), DynamicPart("status", """[^/]+""",true)))
  )
  private[this] lazy val controllers_FriendshipRequestController_changeStatus23_invoker = createInvoker(
    FriendshipRequestController_3.changeStatus(fakeValue[Long], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.FriendshipRequestController",
      "changeStatus",
      Seq(classOf[Long], classOf[String]),
      "POST",
      this.prefix + """friends/requests/""" + "$" + """id<[^/]+>/""" + "$" + """status<[^/]+>""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:51
  private[this] lazy val controllers_CommentController_create24_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("comments")))
  )
  private[this] lazy val controllers_CommentController_create24_invoker = createInvoker(
    CommentController_1.create,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CommentController",
      "create",
      Nil,
      "POST",
      this.prefix + """comments""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:52
  private[this] lazy val controllers_CommentController_getComments25_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("comments/"), DynamicPart("postId", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CommentController_getComments25_invoker = createInvoker(
    CommentController_1.getComments(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CommentController",
      "getComments",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """comments/""" + "$" + """postId<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:54
  private[this] lazy val controllers_CommentController_update26_route = Route("PATCH",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("comments")))
  )
  private[this] lazy val controllers_CommentController_update26_invoker = createInvoker(
    CommentController_1.update,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CommentController",
      "update",
      Nil,
      "PATCH",
      this.prefix + """comments""",
      """""",
      Seq("""nocsrf""")
    )
  )

  // @LINE:56
  private[this] lazy val controllers_CommentController_remove27_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("comments/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CommentController_remove27_invoker = createInvoker(
    CommentController_1.remove(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CommentController",
      "remove",
      Seq(classOf[Long]),
      "DELETE",
      this.prefix + """comments/""" + "$" + """id<[^/]+>""",
      """""",
      Seq("""nocsrf""")
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_7.index())
      }
  
    // @LINE:8
    case controllers_HomeController_explore1_route(params@_) =>
      call { 
        controllers_HomeController_explore1_invoker.call(HomeController_7.explore())
      }
  
    // @LINE:9
    case controllers_HomeController_tutorial2_route(params@_) =>
      call { 
        controllers_HomeController_tutorial2_invoker.call(HomeController_7.tutorial())
      }
  
    // @LINE:13
    case controllers_Assets_versioned3_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned3_invoker.call(Assets_6.versioned(path, file))
      }
  
    // @LINE:16
    case controllers_PostController_create4_route(params@_) =>
      call { 
        controllers_PostController_create4_invoker.call(PostController_0.create)
      }
  
    // @LINE:17
    case controllers_PostController_getMyPosts5_route(params@_) =>
      call { 
        controllers_PostController_getMyPosts5_invoker.call(PostController_0.getMyPosts)
      }
  
    // @LINE:18
    case controllers_PostController_getUsersPosts6_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_PostController_getUsersPosts6_invoker.call(PostController_0.getUsersPosts(id))
      }
  
    // @LINE:19
    case controllers_PostController_getFriendsPosts7_route(params@_) =>
      call { 
        controllers_PostController_getFriendsPosts7_invoker.call(PostController_0.getFriendsPosts)
      }
  
    // @LINE:21
    case controllers_PostController_update8_route(params@_) =>
      call { 
        controllers_PostController_update8_invoker.call(PostController_0.update)
      }
  
    // @LINE:23
    case controllers_PostController_remove9_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_PostController_remove9_invoker.call(PostController_0.remove(id))
      }
  
    // @LINE:25
    case controllers_UserController_getUsersFriends10_route(params@_) =>
      call { 
        controllers_UserController_getUsersFriends10_invoker.call(UserController_4.getUsersFriends)
      }
  
    // @LINE:26
    case controllers_UserController_getMyData11_route(params@_) =>
      call { 
        controllers_UserController_getMyData11_invoker.call(UserController_4.getMyData)
      }
  
    // @LINE:27
    case controllers_UserController_getOtherUserData12_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_UserController_getOtherUserData12_invoker.call(UserController_4.getOtherUserData(id))
      }
  
    // @LINE:29
    case controllers_UserController_updateUserData13_route(params@_) =>
      call { 
        controllers_UserController_updateUserData13_invoker.call(UserController_4.updateUserData())
      }
  
    // @LINE:30
    case controllers_UserController_search14_route(params@_) =>
      call(params.fromPath[String]("term", None)) { (term) =>
        controllers_UserController_search14_invoker.call(UserController_4.search(term))
      }
  
    // @LINE:33
    case controllers_ReactionController_like15_route(params@_) =>
      call(params.fromPath[Long]("postId", None)) { (postId) =>
        controllers_ReactionController_like15_invoker.call(ReactionController_5.like(postId))
      }
  
    // @LINE:35
    case controllers_ReactionController_dislike16_route(params@_) =>
      call(params.fromPath[Long]("postId", None)) { (postId) =>
        controllers_ReactionController_dislike16_invoker.call(ReactionController_5.dislike(postId))
      }
  
    // @LINE:37
    case controllers_ReactionController_removeReaction17_route(params@_) =>
      call(params.fromPath[Long]("postId", None)) { (postId) =>
        controllers_ReactionController_removeReaction17_invoker.call(ReactionController_5.removeReaction(postId))
      }
  
    // @LINE:40
    case controllers_LoginController_login18_route(params@_) =>
      call { 
        controllers_LoginController_login18_invoker.call(LoginController_2.login())
      }
  
    // @LINE:41
    case controllers_LoginController_register19_route(params@_) =>
      call { 
        controllers_LoginController_register19_invoker.call(LoginController_2.register())
      }
  
    // @LINE:43
    case controllers_FriendshipRequestController_getReceivedRequests20_route(params@_) =>
      call { 
        controllers_FriendshipRequestController_getReceivedRequests20_invoker.call(FriendshipRequestController_3.getReceivedRequests)
      }
  
    // @LINE:44
    case controllers_FriendshipRequestController_getSentRequests21_route(params@_) =>
      call { 
        controllers_FriendshipRequestController_getSentRequests21_invoker.call(FriendshipRequestController_3.getSentRequests)
      }
  
    // @LINE:46
    case controllers_FriendshipRequestController_create22_route(params@_) =>
      call { 
        controllers_FriendshipRequestController_create22_invoker.call(FriendshipRequestController_3.create)
      }
  
    // @LINE:48
    case controllers_FriendshipRequestController_changeStatus23_route(params@_) =>
      call(params.fromPath[Long]("id", None), params.fromPath[String]("status", None)) { (id, status) =>
        controllers_FriendshipRequestController_changeStatus23_invoker.call(FriendshipRequestController_3.changeStatus(id, status))
      }
  
    // @LINE:51
    case controllers_CommentController_create24_route(params@_) =>
      call { 
        controllers_CommentController_create24_invoker.call(CommentController_1.create)
      }
  
    // @LINE:52
    case controllers_CommentController_getComments25_route(params@_) =>
      call(params.fromPath[Long]("postId", None)) { (postId) =>
        controllers_CommentController_getComments25_invoker.call(CommentController_1.getComments(postId))
      }
  
    // @LINE:54
    case controllers_CommentController_update26_route(params@_) =>
      call { 
        controllers_CommentController_update26_invoker.call(CommentController_1.update)
      }
  
    // @LINE:56
    case controllers_CommentController_remove27_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_CommentController_remove27_invoker.call(CommentController_1.remove(id))
      }
  }
}
