This is the Maven project structure for Mimosa UI.

To get started, clone the project and run `mvn clean install`
Deploy Mimosa.war in the apache tomcat 7.0.62 or later. And start the server.

Then the application can be accessed at
https://{hostname}:{portnumber}/Mimosa/


Below details are from Mimosa UI project:
# Introduction

The Mimosa UI is built using [React](https://facebook.github.io/react/), [Redux](http://redux.js.org/), [React-Router](https://github.com/ReactTraining/react-router), and [Webpack](https://webpack.github.io/), along with a number of other modules that can be found in the package.json folder. It also uses [Express](https://expressjs.com/) to handle authentication as well as some basic routing.

## React Style Guide

The style guide we are using is very similar to Airbnb's React Style Guide. [Read more about it here ](https://github.com/airbnb/javascript/tree/master/react)

## Directory Structure

The Mimosa UI is currently using React, React-Router, React-Redux, and Express to render the pages and serve static assets.

### UI directory

This directory holds everything that makes the UI of the application. It is separated into components, layouts, pages, and partials folders. These directories have been created and adjusted as the project has been built and as we learned more about React and Redux, so it could probably be adjusted further.

#### Components directory
Everything in the components folder should be used for presentation. It also contains event listeners that are used to perform different tasks, as well as dispatching AJAX events whenever it is needed.

#### Layouts directory
The layouts directory is used for overall page scaffolding. For example, if the page has a sidebar on the left, it should use the sidebar layout component. Subviews will then be rendered in the `{this.props.children}` portion of the template. [The taxonomies page](https://gitlab.sd.apple.com/cx-prototyping/mimosa-components/blob/devProto/ui/pages/taxonomies.js) has a good example of how to use these layout components.

#### Pages and Containers directory
The pages directory are the top level pages for each route. In most cases, theses are connected to the Container components. The Container components connect to the Redux store. [You can read more about Container components and redux here.](http://redux.js.org/docs/basics/UsageWithReact.html)

#### Partials directory
The partials directory are used when only a subview (or portion) of a page is changed.

### Routes directory
The routes directory is where we create our routing for this application. We're currently using React-Router for this, but routing could be handled a number of different ways. Each route hooks up to a container or a layout component. Any layout that has children needs to have `this.props.children` defined in the layout. The subview / page will be rendered at that location in the JSX template.

[Click here to learn more about React-Router](https://github.com/ReactTraining/react-router)

### Store directory
A simple file that is used to hook up our reducers to the redux store. [Click here to learn more about the reducers and the Redux store](http://redux.js.org/docs/basics/Store.html)

### Actions and Reducers directories
Learn more about actions and reducers at the Redux website.
[Redux Actions](http://redux.js.org/docs/basics/Actions.html)
[Redux Reducers](http://redux.js.org/docs/basics/Reducers.html)

### Styles directory
Contains the CSS used for the application. [CSS is being loaded using Webpack](https://webpack.github.io/docs/stylesheets.html)


### Public directory
Contains static assets for the project. Also contains the index.html file that is used to render our entire application.

### Helpers directory
Contains a helper method used for AppleConnect authentication.

### Dev directory
Created to allow a user to bypass the authentication methods that are currently being used in the project.
