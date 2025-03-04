from backend.src import app
import pytest
from fastapi.testclient import TestClient


client = TestClient(app)

def test_app_returns_ok():
    response = client.get("/health")
    assert response.status_code == 200
    assert response.json() == {"status": "ok"}

