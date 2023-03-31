// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers {

  // @LINE:13
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:13
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:43
  class ReverseFriendshipRequestController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:43
    def getReceivedRequests: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "friends/requests/received")
    }
  
    // @LINE:44
    def getSentRequests: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "friends/requests/sent")
    }
  
    // @LINE:46
    def create: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "friends/requests")
    }
  
    // @LINE:48
    def changeStatus(id:Long, status:String): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "friends/requests/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)) + "/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("status", status)))
    }
  
  }

  // @LINE:33
  class ReverseReactionController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:33
    def like(postId:Long): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "reactions/like/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("postId", postId)))
    }
  
    // @LINE:35
    def dislike(postId:Long): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "reactions/dislike/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("postId", postId)))
    }
  
    // @LINE:37
    def removeReaction(postId:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "reactions/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("postId", postId)))
    }
  
  }

  // @LINE:25
  class ReverseUserController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:25
    def getUsersFriends: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "users/friends")
    }
  
    // @LINE:30
    def search(term:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "users/search/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("term", term)))
    }
  
    // @LINE:27
    def getOtherUserData(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "users/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)) + "/profile")
    }
  
    // @LINE:26
    def getMyData: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "users/me/profile")
    }
  
    // @LINE:29
    def updateUserData(): Call = {
      
      Call("PATCH", _prefix + { _defaultPrefix } + "users/me/profile")
    }
  
  }

  // @LINE:7
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
    // @LINE:8
    def explore(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "explore")
    }
  
    // @LINE:9
    def tutorial(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "tutorial")
    }
  
  }

  // @LINE:40
  class ReverseLoginController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:40
    def login(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "auth/login")
    }
  
    // @LINE:41
    def register(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "auth/register")
    }
  
  }

  // @LINE:16
  class ReversePostController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def create: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "posts")
    }
  
    // @LINE:19
    def getFriendsPosts: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "posts")
    }
  
    // @LINE:18
    def getUsersPosts(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "posts/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:17
    def getMyPosts: Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "posts/me")
    }
  
    // @LINE:23
    def remove(id:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "posts/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:21
    def update: Call = {
      
      Call("PATCH", _prefix + { _defaultPrefix } + "posts")
    }
  
  }

  // @LINE:51
  class ReverseCommentController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:51
    def create: Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "comments")
    }
  
    // @LINE:52
    def getComments(postId:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "comments/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("postId", postId)))
    }
  
    // @LINE:54
    def update: Call = {
      
      Call("PATCH", _prefix + { _defaultPrefix } + "comments")
    }
  
    // @LINE:56
    def remove(id:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "comments/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
  }


}
