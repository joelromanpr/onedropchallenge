*Disclaimer, the MAD score for Kotlin % also takes into account libraries, my implementation of the project is completly in Kotlin but some libraries used might not be.
![MAD Score](https://github.com/joelromanpr/onedropchallenge/blob/main/screenshots/summary.png)
### Architecture
- This project follows the original instructions guidance of using MVP
- For each applicable feature (features:```feature-weather```) you will find its contract Presenter/View
- Coroutines are used to fetch information from the network, coroutines are the recommended and widely adopted API these days
- One approach to modularization was applied to showcase a few things:
    - Modularization helps separating concerns across layers
    - Improves build time first build is half the time vs no modularization, subsequent builds are even less thanks to caching of modules
    - In this approach I broke it down in the following:
        - ```:app``` = The application
        - ```:app-shared``` = Anything shared across features, eg. Navigator
        - models = ```:remote``` holds network models, ```:application``` feature specific models
        - ```:utils``` = view extensions
        - :data
            - ```:data-remote``` = holds api contracts (interfaces) this is our retrofit definitions
            - ```:data-remote-impl``` = wiring (dagger/hilt) and anything concrete, eg. build retrofit instances
            - ```:data-repo``` = holds the api contracts for repository, this is what features reference
            - ```:data-repo-impl``` = the concrete implementations and wiring for repositories this is reference in the app not features themselves
            - ```feature-weather``` = The Current weather feature, can fetch weather info for NY or current location
            - ```:feature-forecast``` = The forecast feature that takes care of showing a list of the weather forecast
            
### Additional libraries and takeaways            
- Hilt/Dagger : Use for dependency injection, a  principle within SOLID principles (D = dependency inversion)  
- Separated wire models (networks) from app specific ones based on needs per README
- Soft introduction to coroutines : took a chance to showcase how can we adopt them 
- Navigator class to switch between features : 1 so far, details this also helps when we are modularizing an application and our features are by modules
- Following a repository pattern to abstract data layer from view layer
- Location services was implemented using a 3rd party library in the interested of time
- illustrations taken from open source website https://undraw.co
- [screenshots](https://github.com/joelromanpr/onedropchallenge/tree/main/screenshots)

### Potential future work
- Illustrate how to modularize for bigger scale, think of Square, their strategy consist of applying dependency inversion on modules themselves, so you have an api- -impl & impl-wiring module
- UI work
- Tests
