class Rectangle {
    int xLow, xUp;
    int yLow, yUp;
    Rectangle(int x1,int y1,int x2,int y2) {
        xLow = x1;
        xUp = x2;
        yLow = y1;
        yUp = y2;
    }
    Rectangle(Point p1, Point p2) {
        xLow = p1.x;
        xUp = p2.x;
        yLow = p1.y;
        yUp = p2.y;
    }
    int area() { return (xUp - xLow) * (yUp - yLow); }
    int perimeter() { return (xUp - xLow)*2 + (yUp - yLow)*2; }
    boolean pointInside(Point p) { return (p.x >= this.xLow && p.x <= this.xUp) && (p.y >= this.yLow && p.y <= this.yUp); }
    boolean rectangleInside(Rectangle r) {
        Point up = new Point(r.xUp, r.yUp);
        Point down = new Point(r.xLow, r.yLow);
        return this.pointInside(up) && this.pointInside(down);
    }
}