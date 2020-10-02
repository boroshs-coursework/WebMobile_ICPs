import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-search-recipe',
  templateUrl: './search-recipe.component.html',
  styleUrls: ['./search-recipe.component.css']
})
export class SearchRecipeComponent implements OnInit {
  // does this instead of passing recipe and places to getVenues
  @ViewChild('recipe') recipes: ElementRef;
  @ViewChild('place') places: ElementRef;
  recipeValue: any;
  placeValue: any;

  // variables that will store the values from get response
  venueList = [];
  recipeList = [];
  currentLat: any;
  currentLong: any;
  geolocationPosition: any;
  
  // API ids and keys
  recipieAppId: string = '5499966c';
  recipieAppKey: string = '8f371f0e8673b31317059766d0c1f232';
  fsClientId: string = 'UJC4FXQI0POL2WQTTELARDRP2UQSRHR4C0VBYBMN5NZXRSMA';
  fsClientSecret: string = 'UAZIYNHPFCV4YC5PEUVNDFVWKWQGJXTDL5TGQKHIRXXJR4K2';


  constructor(private _http: HttpClient) {
  }

  ngOnInit() {

    window.navigator.geolocation.getCurrentPosition(
      position => {
        this.geolocationPosition = position;
        this.currentLat = position.coords.latitude;
        this.currentLong = position.coords.longitude;
      });
  }

  getVenues() {
    // values from the form
    this.recipeValue = this.recipes.nativeElement.value;
    this.placeValue = this.places.nativeElement.value;

    if (this.recipeValue !== null) { // runs if you submitted a input
      console.log(this.recipeValue); // print the search value
      // code to use EDAMAM API
      this._http.get(`https://api.edamam.com/search?q=${this.recipeValue}&app_id=${this.recipieAppId}&app_key=${this.recipieAppKey}`)
                .subscribe((recipieResp: any) =>
                  {// recipeList will populate html template
                    // get the recipies from hits to fill recipeList
                    // for each recipie element get label, url, calories, yield, and image
                    this.recipeList = Object.keys(recipieResp.hits).map((ind) =>{
                      var oneRecipe = recipieResp.hits[ind].recipe;
                      return {
                        name: oneRecipe.label,
                        url: oneRecipe.url,
                        calories: Math.floor(oneRecipe.calories),
                        yield: oneRecipe.yield,
                        icon: oneRecipe.image}
                    });
                    console.log(this.recipeList);// print allocated rsp to console
                  }, error =>{alert("API response error for EDAMAM! Try again.");}
                );
    } // end of EDAMAM or recipie if statement

    if (this.placeValue != null && this.placeValue !== '' && this.recipeValue != null && this.recipeValue !== '') { // if there's a input
      console.log(this.placeValue); // print the search value
      // code to use Foursquare API&near=${this.placeValue}&query=${this.recipeValue}%20restaurant
      this._http.get(`https://api.foursquare.com/v2/venues/search?client_id=${this.fsClientId}&client_secret=${this.fsClientSecret}&near=${this.placeValue}&query=${this.recipeValue}&v=20200901`)
                .subscribe((venResp: any) =>
                  {
                    this.venueList = Object.keys(venResp.response.venues).map((ind) =>{
                      var oneVen = venResp.response.venues[ind];
                      return {
                        name: oneVen.name,
                        currentLat: oneVen.location.lat,
                        currentLong: oneVen.location.lng,
                        loc: oneVen.location.formattedAddress}
                    });
                    console.log(this.venueList);// print allocated rsp to console
                  }, error =>{alert("API response error for FourSquare! Try again.");}
                );
    } // end of foursquare-html-GET if statement
  }
}