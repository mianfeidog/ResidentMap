(function(win){
	var utils = {
			set:function(key,val){
				localStorage.setItem(key,val);
			},
			get:function(key){
				return localStorage.getItem(key);
			},
			remove:function(key){
				localStorage.removeItem(key);
			}
	};
	win.localManager = {
		utils:utils,	
	};
})(window);