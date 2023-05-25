import { ApplicationRef, enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

import { AppModule } from './app/app.module';
import { environment } from './environments/environment';
import { AppComponent } from './app/app.component';

if (environment.production) {
  enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule)
  .then((moduleRef) => {
    const appRef = moduleRef.injector.get(ApplicationRef);
    const componentRef = appRef.components[0];
    // Ensure that AppComponent is used as the root component
    if (componentRef.componentType === AppComponent) {
      // Optional: You can do additional initialization here
    } else {
      console.error('AppComponent not found');
    }
  })
  .catch(err => console.error(err));
