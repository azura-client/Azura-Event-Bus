# Azura-Event-Bus
###### A simple EventBus made for daily use.

## Maven [![](https://jitpack.io/v/azura-client/Azura-Event-Bus.svg)](https://jitpack.io/#azura-client/Azura-Event-Bus)
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.azura-client</groupId>
    <artifactId>Azura-Event-Bus</artifactId>
    <version>Tag</version>
  </dependency>
</dependencies>
```

## License
GPL-3.0 License

It's Error's balls code, not mine
 -Solastis

## How to migrate from 1.x.x to 2.0.0
First off, EventPriority order has been swapped. So EventPriority.HIGHEST in 1.x.x is now EventPriority.LOWEST.
Second, EventCancellable has been renamed to CancellableEvent.
Next, EventPriority has been moved from the register method to be set inside each @EventHandler annotation to allow for more customizability.
