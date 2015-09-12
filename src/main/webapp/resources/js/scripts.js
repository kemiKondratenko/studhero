/* 
 * Main Application Model START
 */
function AppViewModel() {
    
    var self = this;
    var Nav = Navigation();
    var AppDate = ApplicationDate();   
    
    Nav.setTitle();
    
    self.currentYear = ko.observable(AppDate.year);
    self.currentPage = ko.observable(Nav.currentPage);
    self.showCreateEvent = Nav.showCreateEvent;
    self.showMain = Nav.showMain;
    
    self.scrollToHowItWorks = function(){
        jQuery('html,body').animate({
            scrollTop: jQuery('#how-it-works').offset().top - 60
        }, 1000);
    };
    
    self.setUser = function(user){
        self.user(user);
    }
    self.user = ko.observable("student");    
    
    self.post = function(postData){
        jQuery.ajax({
            type: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            url: 'http://89.184.67.220:8080/StudHero/db/events/getEvents',
            data: '',
            dataType: 'json',
            success: function(data){
                console.log(data);
            },
            error: function(jqXHR, textStatus, ex) {
                console.log(textStatus + ", " + ex + ", " + jqXHR.responseText);
            },
            complete: function(data){
                console.log(data);
            }
          });
    };
    
    self.post(); 
}

/* 
 * Main Application Model END
 */

/* 
 * Navigation START
 */
var Navigation = function(){ 
    var titleMain = "StudHero";
    var sep = " | ";
    var ext = ".jsp";
    var indexPage = "index.jsp";
    var newEventPage = "create-event.jsp";
    var currentPage = window.location.pathname.replace('/','');
    var navObj = {
        showCreateEvent: function(){
            window.location.pathname = newEventPage;
        },
        showMain: function(){
            window.location.pathname = indexPage;
        },
        setTitle: function(){
            switch (currentPage){
                case "index" + ext: 
                    document.title = titleMain + sep + "Головна"; break;
                case "events" + ext: 
                    document.title = titleMain + sep + "Події"; break;
                case "faq" + ext: 
                    document.title = titleMain + sep + "FAQ"; break;
               case "login" + ext: 
                    document.title = titleMain + sep + "Вхід"; break;
               case "registration" + ext: 
                    document.title = titleMain + sep + "Реєстрація"; break;
            }
        },
        currentPage: currentPage
    };
    return navObj;
};
/* 
 * Navigation END
 */

/* 
 * Application Date START
 */
var ApplicationDate = function(){
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth();
    var day = date.getDay();
    var dateObj = {
        year: year,
        getFullDate: function(){
            return day + "." + month + "." + year;
        }
    };
    return dateObj;
};
/* 
 * Application Date END
 */



ko.applyBindings(new AppViewModel());