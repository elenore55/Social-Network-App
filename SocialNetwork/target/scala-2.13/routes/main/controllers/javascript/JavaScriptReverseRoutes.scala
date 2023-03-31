// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers.javascript {

  // @LINE:13
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:43
  class ReverseFriendshipRequestController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:43
    def getReceivedRequests: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.FriendshipRequestController.getReceivedRequests",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "friends/requests/received"})
        }
      """
    )
  
    // @LINE:44
    def getSentRequests: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.FriendshipRequestController.getSentRequests",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "friends/requests/sent"})
        }
      """
    )
  
    // @LINE:46
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.FriendshipRequestController.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "friends/requests"})
        }
      """
    )
  
    // @LINE:48
    def changeStatus: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.FriendshipRequestController.changeStatus",
      """
        function(id0,status1) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "friends/requests/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0)) + "/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("status", status1))})
        }
      """
    )
  
  }

  // @LINE:33
  class ReverseReactionController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:33
    def like: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ReactionController.like",
      """
        function(postId0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "reactions/like/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("postId", postId0))})
        }
      """
    )
  
    // @LINE:35
    def dislike: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ReactionController.dislike",
      """
        function(postId0) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "reactions/dislike/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("postId", postId0))})
        }
      """
    )
  
    // @LINE:37
    def removeReaction: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ReactionController.removeReaction",
      """
        function(postId0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "reactions/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("postId", postId0))})
        }
      """
    )
  
  }

  // @LINE:25
  class ReverseUserController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:25
    def getUsersFriends: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.getUsersFriends",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/friends"})
        }
      """
    )
  
    // @LINE:30
    def search: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.search",
      """
        function(term0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/search/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("term", term0))})
        }
      """
    )
  
    // @LINE:27
    def getOtherUserData: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.getOtherUserData",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0)) + "/profile"})
        }
      """
    )
  
    // @LINE:26
    def getMyData: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.getMyData",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "users/me/profile"})
        }
      """
    )
  
    // @LINE:29
    def updateUserData: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.UserController.updateUserData",
      """
        function() {
          return _wA({method:"PATCH", url:"""" + _prefix + { _defaultPrefix } + """" + "users/me/profile"})
        }
      """
    )
  
  }

  // @LINE:7
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
    // @LINE:8
    def explore: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.explore",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "explore"})
        }
      """
    )
  
    // @LINE:9
    def tutorial: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.tutorial",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tutorial"})
        }
      """
    )
  
  }

  // @LINE:40
  class ReverseLoginController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:40
    def login: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LoginController.login",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "auth/login"})
        }
      """
    )
  
    // @LINE:41
    def register: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.LoginController.register",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "auth/register"})
        }
      """
    )
  
  }

  // @LINE:16
  class ReversePostController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PostController.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "posts"})
        }
      """
    )
  
    // @LINE:19
    def getFriendsPosts: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PostController.getFriendsPosts",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "posts"})
        }
      """
    )
  
    // @LINE:18
    def getUsersPosts: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PostController.getUsersPosts",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "posts/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:17
    def getMyPosts: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PostController.getMyPosts",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "posts/me"})
        }
      """
    )
  
    // @LINE:23
    def remove: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PostController.remove",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "posts/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:21
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.PostController.update",
      """
        function() {
          return _wA({method:"PATCH", url:"""" + _prefix + { _defaultPrefix } + """" + "posts"})
        }
      """
    )
  
  }

  // @LINE:51
  class ReverseCommentController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:51
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CommentController.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "comments"})
        }
      """
    )
  
    // @LINE:52
    def getComments: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CommentController.getComments",
      """
        function(postId0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "comments/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("postId", postId0))})
        }
      """
    )
  
    // @LINE:54
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CommentController.update",
      """
        function() {
          return _wA({method:"PATCH", url:"""" + _prefix + { _defaultPrefix } + """" + "comments"})
        }
      """
    )
  
    // @LINE:56
    def remove: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.CommentController.remove",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "comments/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
  }


}
