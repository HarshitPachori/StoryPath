import React from "react";
import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
} from "react-router-dom";
import { Toaster } from "react-hot-toast";
import Layout from "./components/Layout.jsx";
import ReduxProvider from "./components/ReduxProvider.jsx";
import VerifyAccountPage from "./pages/VerifyAccountPage.jsx";

const HomePage = React.lazy(() => import("./pages/HomePage.jsx"));
const LoginPage = React.lazy(() => import("./pages/LoginPage.jsx"));
const SignUpPage = React.lazy(() => import("./pages/SignUpPage.jsx"));
const NotFound = React.lazy(() => import("./pages/NotFound.jsx"));
const PrivateRoute = React.lazy(() => import("./components/PrivateRoute.jsx"));
const JournalPage = React.lazy(() => import("./pages/JournalPage.jsx"));
const AboutPage = React.lazy(() => import("./pages/AboutPage.jsx"));
const ContactPage = React.lazy(() => import("./pages/ContactPage.jsx"));

const myRouter = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<Layout />}>
      <Route path="" element={<HomePage />} />
      <Route path="login" element={<LoginPage />} />
      <Route path="signup" element={<SignUpPage />} />
      <Route path="about" element={<AboutPage />} />
      <Route path="contact" element={<ContactPage />} />
      <Route path="verify-account" element={<VerifyAccountPage />} />
      <Route path="journal" element={<PrivateRoute />}>
        <Route path="" element={<JournalPage />} />
      </Route>
      <Route path="*" element={<NotFound />} />
    </Route>
  )
);

function App() {
  return (
    <ReduxProvider>
      <RouterProvider router={myRouter} />
      <Toaster />
    </ReduxProvider>
  );
}

export default App;
