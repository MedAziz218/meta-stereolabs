# meta-stereolabs

⚠️ **Work in Progress**

---

This is a Yocto layer that provides recipes for using [**Stereolabs ZED cameras**](https://www.stereolabs.com/developers) on **NVIDIA Jetson boards**.  
It depends on the `meta-tegra` layer and includes support for:  

- [ZED SDK](https://www.stereolabs.com/developers/)  
- [pyzed](https://github.com/stereolabs/zed-python-api)
- [ZED ROS 2 wrapper](https://github.com/stereolabs/zed-ros2-wrapper) (depends on `meta-ros` layer)

---

## How to use

1. Clone this layer into your Yocto project:  
   ```bash
   git clone https://github.com/MedAziz218/meta-stereolabs.git
    ```

2. Add it to your `bblayers.conf`.
3. Build your image with the required packages, for example:

   ```bitbake
   IMAGE_INSTALL:append = " zed-sdk python3-pyzed zed-wrapper "
   ```

